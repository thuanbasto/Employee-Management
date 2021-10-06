import { MessageResponse } from './../../../../shared/model/message-response';
import { Department } from './../../../../shared/model/department';
import { DepartmentValidator } from '../../../../shared/validator/department.validator';
import { FormValidator } from '../../../../shared/validator/form.validator';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Modal } from '../../../../shared/model/modal';
import { Component, OnInit } from "@angular/core";
import { DepartmentService } from "src/app/shared/services/department.service";
import { ToastrService } from 'ngx-toastr';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { fromEvent } from 'rxjs';
import Swal from 'sweetalert2';

declare var $: any;

@Component({
  selector: "app-department-list",
  templateUrl: "./department-list.component.html",
  styleUrls: ["./department-list.component.css"],
})
export class DepartmentListComponent implements OnInit {
  form = new FormGroup({
    department_id: new FormControl({ value: null, disabled: true }),
    departmentName: new FormControl("", [Validators.required, Validators.maxLength(100)],
    DepartmentValidator.isDuplicateDepartmentName(this.departmentService)),
  });

  // for paging
  departments: any = [];
  size: number = 10;
  page: number = 1;
  pageTotal: number;
  numberTotal: number;
  search: string = "";
  sortBy: string = "department_id";
  sortDirection: string = "DESC";
  // end paging
  id = 0;

  constructor(
    private toastr: ToastrService,
    private departmentService: DepartmentService,
  ) {}

  ngOnInit() {
    this.loopFocus();
    this.searchDepartment();

    fromEvent($("#search"), 'keyup').pipe(
      map((event: any) => {
        return event.target.value;
      })
      , debounceTime(500)
      , distinctUntilChanged()
    ).subscribe((text: string) => {
      this.searchDepartment();
    },
    err => {
      console.log(err);
    });
  }


  // obj = {
  //   size : this.size,
  //   page : this.page,
  // }
  searchDepartment(obj?: any) {
    let key = typeof obj;

    switch (key) {
      case "object":
        this.page = obj.page;
        this.size = obj.size;
        break;
      case "boolean":
        break;
      default:
        this.page = 1;
        break;
    }

    let search = this.search.trim();

    this.departmentService.pagingBySearch(search, this.page, this.size, this.sortBy, this.sortDirection).subscribe(
      response => this.departments = response
    );

    this.departmentService.numberTotalBySearch(search).subscribe(
      response => {
      this.numberTotal = response as number;
      this.pageTotal = Math.ceil(this.numberTotal / this.size);
    });
  }

  changeSortBy(sortBy) {
    if (this.sortBy == sortBy) {
      this.sortDirection = this.sortDirection == "DESC" ? "ASC" : "DESC";
    } else {
      this.sortBy = sortBy;
      this.sortDirection = "DESC";
    }
    this.searchDepartment(true);
  }

  showAddDepartmentModal() {
    this.form.reset();
    this.id = null;
  }

  showUpdateDepartmentModal(department: Department) {
    this.form.reset();

    this.departmentService.getByDepartmentName(department.departmentName).subscribe(
      (response: any) => {
        // show modal add / update
        $("#addDepartmentModal").modal('show');
        if (response) {
          this.id = response.department_id;
          this.department_id.setValue(response.department_id);
          this.departmentName.setValue(response.departmentName);
        }
      },
      err => {
        this.alertConfirmError("This item already deleted");
      }
    )


  }

  deleteDepartment(id) {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        this.departmentService.delete(id).subscribe(
          (response) => {
            this.searchDepartment(true);
            this.toastr.success('Deleted Successfully');
          },
          (err) => {
            console.log(err);
            if (err.status == 404) {
              this.alertConfirmError("This item already deleted")
            } if (err.status == 409) {
              let error: MessageResponse = err.error;
              Swal.fire(
                error.message,
                "",
                'error'
              )
            }
          }
        );
      }
    });
  }

  alertConfirmError(text) {
    Swal.fire({
      title: text,
      icon: "error",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Reload page!",
    }).then((result) => {
      if (result.isConfirmed) {
        this.searchDepartment(true);
      }
    })
  }

  saveDepartment() {
    let check = true;
    check = FormValidator.validForm(this.form);

    if (check) {
      let obj = this.form.value;
      this.addOrUpdate(obj);
    } else {
      FormValidator.focusErrorField();
    }
  }

  addOrUpdate(obj) {
    if (this.id) {
      this.departmentService.update(this.id, obj).subscribe(
        (response) => {
          $("#btnClose").click();
          this.searchDepartment();
          this.toastr.success('Updated successfully');
        },
        (err) => {
          console.log(err);
          if (err.status == 409) {
            this.toastr.error((err.error as MessageResponse).message);
          }
        }
      );
    } else {
      this.departmentService.create(obj).subscribe(
        (response) => {
          this.searchDepartment();
          this.toastr.success('Added successfully');
          $("#btnClose").click();
        },
        (err) => {
          console.log(err);
          if (err.status == 409) {
            this.toastr.error('Add failed');
          }
        }
      );
    }
  }

  checkedAll(event) {
    $(".chk_delete").prop("checked", event.target.checked);
  }

  deleteDepartments() {
    let ids = [];
    $(".chk_delete:checked").each(function() {
      ids.push(+$(this).val());
    })
    if (ids.length > 0) {
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
      }).then((result) => {
        if (result.isConfirmed) {
          this.departmentService.deletes(ids).subscribe(
            response => {
              console.log(response);
              this.searchDepartment(true);
              this.toastr.success("Deleted successfully")
            },
            error => {
              console.log(error)
              if (error.status == 409) {
                let messageResponse: MessageResponse = error.error;
                Swal.fire(
                  messageResponse.message,
                  "",
                  'error'
                )
              } else {
                Swal.fire({
                  title: "This id already deleted",
                  icon: "error",
                  showCancelButton: true,
                  confirmButtonColor: "#3085d6",
                  cancelButtonColor: "#d33",
                  confirmButtonText: "Reload page!",
                }).then((result) => {
                  if (result.isConfirmed) {
                    this.searchDepartment(true);
                  }
                });
              }
            }
          )
        }
      })
    } else {
      Swal.fire(
        "Please choose before delete",
        "",
        'error'
      )
    }

  }

  loopFocus() {

    $("#search").focus();

    $(document).ready(function () {
      $("#addDepartmentModal").on("shown.bs.modal", function () {
        $("#departmentName").focus();
      });
    });

    $("#departmentName").on("keydown", function (e) {
      if (e.keyCode == 9) {
        if (e.shiftKey) {
          e.preventDefault();
          $("#btnSave").focus();
        }
      }
    });

    $("#btnSave").on("keydown", function (e) {
      if (e.keyCode == 9) {
        if (!e.shiftKey) {
          e.preventDefault();
          $("#departmentName").focus();
        }
      }
    });

    $("#btnNext").on("keydown", function (e) {
      if (e.keyCode == 9) {
        if (!e.shiftKey) {
          e.preventDefault();
          $("#search").focus();
        }
      }
    });

    $("#search").on("keydown", function (e) {
      if (e.keyCode == 9) {
        if (e.shiftKey) {
          e.preventDefault();
          $("#btnNext").focus();
        }
      }
    });
  }

  get departmentName() {
    return this.form.get("departmentName");
  }
  get department_id() {
    return this.form.get("department_id");
  }
}
