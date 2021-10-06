import { EmployeeService } from "../../../../shared/services/employee.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { debounceTime, map, distinctUntilChanged } from "rxjs/operators";
import { fromEvent } from "rxjs";
import Swal from "sweetalert2";

declare var $: any;

@Component({
  selector: "app-employee-list",
  templateUrl: "./employee-list.component.html",
  styleUrls: ["./employee-list.component.css"],
})
export class EmployeeListComponent implements OnInit {
  // for paging
  employees: any = [];
  size: number = 10;
  page: number = 1;
  pageTotal: number;
  numberTotal: number;
  search: string = "";
  sortBy: string = "employee_id";
  sortDirection: string = "DESC";
  // end paging

  isLoading = false;

  constructor(
    private employeeService: EmployeeService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    $("#search").focus();
    this.loopFocus();
    this.searchEmployee();

    fromEvent($("#search"), "keyup").pipe(
      map((event: any) => {
        return event.target.value;
      }),
      debounceTime(500),
      distinctUntilChanged()
    )
    .subscribe(
      (text: string) => {
        this.searchEmployee();
      },
      (err) => {
        console.log(err);
      }
    );
  }

  // obj = {
  //   size : this.size,
  //   page : this.page,
  // }
  searchEmployee(obj?: any) {
    let key = typeof obj;

    switch (key) {
      case "object":
        this.page = obj.page;
        this.size = obj.size;
        break;
      case "boolean": // just load page with current parameter
        break;
      default:
        this.page = 1;
        break;
    }

    let search = this.search.trim();
    this.isLoading = true;
    this.employeeService
      .pagingBySearch(
        search,
        this.page,
        this.size,
        this.sortBy,
        this.sortDirection
      )
      .subscribe(
        (response) => {
          this.employees = response;
        },
        (err) => {
          console.log(err);
        },
        () => {
          this.isLoading = false;
        }
      );

    this.employeeService.numberTotalBySearch(search).subscribe((response) => {
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
    this.searchEmployee(true);
  }

  editEmployee(id) {
    this.employeeService.get(id).subscribe(
      (response) => {
        this.router.navigateByUrl(`/admin/employee-add/${id}`);
      },
      (err) => {
        console.log(err);
        this.noExistId();
      }
    );
  }

  noExistId() {
    Swal.fire({
      title: "This id already deleted",
      icon: "error",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Reload page!",
    }).then((result) => {
      if (result.isConfirmed) {
        this.searchEmployee(true);
      }
    });
  }

  deleteEmployee(id) {
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
        this.employeeService.delete(id).subscribe(
          (response) => {
            this.searchEmployee(true);
            this.toastr.success("Deleted successfully");
          },
          (err) => {
            console.log(err);
            if (err.status == 404) {
              this.noExistId();
            }
          }
        );
      }
    });
  }

  checkedAll(event) {
    $(".chk_delete").prop("checked", event.target.checked);
  }

  deleteEmployees() {
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
          this.employeeService.deletes(ids).subscribe(
            response => {
              console.log(response);
              this.searchEmployee(true);
              this.toastr.success("Deleted successfully")
            },
            error => {
              console.log(error)
              if (error.status == 404) {
                this.noExistId();
              } else {
                this.toastr.error("Has error occur")
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
    $("#btnNext").on("keydown", function (e) {
      if (e.keyCode == 9) {
        // tab key
        if (!e.shiftKey) {
          // tab key + shift key
          e.preventDefault();
          $("#search").focus();
        }
      }
    });

    $("#search").on("keydown", function (e) {
      if (e.keyCode == 9) {
        // tab key
        if (e.shiftKey) {
          // tab key + shift key
          e.preventDefault();
          $("#btnNext").focus();
        }
      }
    });
  }
}
