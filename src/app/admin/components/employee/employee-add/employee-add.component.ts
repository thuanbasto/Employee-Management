import { URL_IMAGE } from '../../../../../environments/environment';
import { FileService } from '../../../../shared/services/file.service';
import { FormValidator } from '../../../../shared/validator/form.validator';
import { EmployeeValidator } from '../../../../shared/validator/employee.validator';
import { EmployeeService } from '../../../../shared/services/employee.service';
import { DepartmentService } from '../../../../shared/services/department.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { Location } from '@angular/common';

declare var $: any;

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent implements OnInit {
  departments: any = [];
  id;
  previewImageUrl = "assets/image/default-image.jpg";
  avatarFile: File;
  errorMessageFile = ""

  form = new FormGroup({
    address       : new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    placeOfBirth  : new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    birthday      : new FormControl("", [Validators.required, EmployeeValidator.isBirthday]),
    description   : new FormControl("", [Validators.maxLength(2000)]),
    phone         : new FormControl("", [EmployeeValidator.isPhone]),
    name          : new FormControl("", [EmployeeValidator.isName]),
    married       : new FormControl(false, []),
    imageUrl      : new FormControl(null, []),
    department    : new FormGroup({
      department_id : new FormControl("", [Validators.required]),
    }),
  })

  constructor(
    private employeeService: EmployeeService,
    private departmentService: DepartmentService,
    private toastrService: ToastrService,
    private fileService: FileService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location) { }

  ngOnInit() {
    $("#name").focus()
    this.loopFocus();
    this.departmentService.getAll().subscribe(response => this.departments = response)
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.employeeService.get(this.id).subscribe(
        (response: any) => {
          this.name.setValue(response.name);
          this.address.setValue(response.address);
          this.birthday.setValue(response.birthday);
          this.department_id.setValue(response.department.department_id);
          this.description.setValue(response.description);
          this.phone.setValue(response.phone);
          this.placeOfBirth.setValue(response.placeOfBirth);
          this.married.setValue(response.married);

          if (response.imageUrl != null && response.imageUrl != "") {
            this.imageUrl.setValue(response.imageUrl);
            this.previewImageUrl = URL_IMAGE + "/" + response.imageUrl;
          }

        },
        err => {
          console.log(err);
          Swal.fire({
            title: "This id already deleted",
            icon: "error",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "Return page!",
            allowOutsideClick: false,
          }).then((result) => {
            if (result.isConfirmed) {
              this.location.back()
            }
          })
        }
      )
    }

  }

  previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
      $("#imageSrc").attr("src", reader.result as string);
    }
    if (event.target.files[0] && event.target.files[0].type.includes("image/")) {
      if (event.target.files[0].size/1024 > 1024) {
        this.errorMessageFile = "File is too larger. Can upload file 1MB";
      } else {
        this.errorMessageFile = "";
        this.avatarFile = event.target.files[0];
        reader.readAsDataURL(event.target.files[0]);
      }
    } else {
      if (event.target.value == "") {
        $("#imageSrc").attr("src", "assets/image/default-image.jpg");
      } else {
        this.errorMessageFile = "File must be image";
      }
    }
  }

  addEmployee() {
    console.log("submit");
    this.form.markAllAsTouched();

    let check = true;
    check = FormValidator.validForm(this.form);

    if (check) {
      let obj = this.form.value;

      if ($("#file").val() == "") {
        this.addOrUpdate(obj);
      } else {
        this.uploadFile(obj);
      }
    } else {
      FormValidator.focusErrorField()
    }
  }

  uploadFile(obj) {
    let formData: FormData = new FormData();
    formData.append("file", this.avatarFile);

    this.fileService.create(formData).subscribe(
      (response : any) => {
        console.log(response);
        obj.imageUrl = response.message;
        // after add file success, then add employee
        this.addOrUpdate(obj);
      },
      err => {
        console.log(err);
        this.errorMessageFile = "File is too larger. Can upload file 1MB"
      }
    )
  }

  addOrUpdate(obj) {
    console.log(obj)
    if (this.id) {
      this.employeeService.update(this.id, obj).subscribe(
        response => {
          this.router.navigateByUrl("/admin/employee")
          this.toastrService.success("Updated successfully");
        },
        err => {
          FormValidator.setErrorForField(err.error, this.form);
        }
      )
    } else {
      this.employeeService.create(obj).subscribe(
        response => {
          Swal.fire({
            title: "Added successfully?",
            text: "Are you want add more?",
            icon: "success",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "No",
            confirmButtonText: "Yes, i have!",
          }).then((result) => {
            if (result.isConfirmed) {
              this.form.markAsPristine();
              this.form.markAsUntouched();
              // this.form.reset();
              $("form")[0].reset();
              $("#department_id").val("")
              $("#description").val("");
              $("#imageSrc").attr("src", this.previewImageUrl);
            } else {
              this.router.navigateByUrl("/admin/employee")
            }
          })
          // this.toastrService.success("Added successfully");
        },
        err => {
          FormValidator.setErrorForField(err.error, this.form);
        }
      )
    }
  }

  loopFocus() {
    $("#name").on("keydown", function (e) {
      if(e.keyCode == 9) { // tab key
        e.preventDefault();
        if(e.shiftKey) { // tab key + shift key
          $("#btnSubmit").focus()
        }
        else {
          $("#address").focus()
        }
      }
    })

    $("#btnSubmit").on("keydown", function (e) {
      if(e.keyCode == 9) { // tab key
        e.preventDefault();
        if(e.shiftKey) { // tab key + shift key
          $("#btnFile").focus()
        }
        else {
          $("#name").focus()
        }
      }
    })
  }

  get name() {
    return this.form.get("name");
  }

  get address() {
    return this.form.get("address");
  }

  get description() {
    return this.form.get("description");
  }

  get phone() {
    return this.form.get("phone");
  }

  get placeOfBirth() {
    return this.form.get("placeOfBirth");
  }

  get married() {
    return this.form.get("married");
  }

  get department_id() {
    return this.form.get("department").get("department_id");
  }

  get birthday() {
    return this.form.get("birthday");
  }

  get imageUrl() {
    return this.form.get("imageUrl");
  }
}
