import { AuthService } from './../../../shared/services/auth.service';
import { FormValidator } from './../../../shared/validator/form.validator';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.minLength(6) , Validators.maxLength(255)]),
    password: new FormControl("", [Validators.required, Validators.minLength(6) , Validators.maxLength(255)]),
    passwordConfirm: new FormControl("", [Validators.required, Validators.minLength(6) , Validators.maxLength(255)])
  });

  isMatchPassword = true;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    $("#username").focus();
    this.loopFocus();
  }

  register() {
    let check = true;
    check = FormValidator.validForm(this.form);
    this.form.markAllAsTouched();

    if (check && this.isMatchPassword) {
      console.log(this.form.value);
      this.authService.register(this.form.value).subscribe(
        res => {
          console.log(res);
          Swal.fire({
            title: 'Resgister successfully',
            icon: 'success',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Login now'
          }).then((result) => {
            if (result.isConfirmed) {
              this.router.navigate(["/login"]);
            } else {
              this.form.reset();
            }
          })
        },
        err => {
          console.log(err);
          FormValidator.setErrorForField(err.error, this.form);
        }
      )
    } else {
      FormValidator.focusErrorField();
    }
  }

  checkMatchPassword() {
    if (this.password.value != "" && this.passwordConfirm.value != "") {
      if (this.password.value != this.passwordConfirm.value && this.password.valid && this.passwordConfirm.valid) {
        this.isMatchPassword = false;
      } else {
        this.isMatchPassword = true;
      }
    } else {
      this.isMatchPassword = true;
    }
  }

  loopFocus() {
    $("#username").on("keydown", function (e) {
      if(e.keyCode == 9) { // tab key
        if(e.shiftKey) { // tab key + shift key
          e.preventDefault();
          $("#btnSubmit").focus()
        }
      }
    })

    $("#btnSubmit").on("keydown", function (e) {
      if(e.keyCode == 9) { // tab key
        if(!e.shiftKey) { // tab key + shift key
          e.preventDefault();
          $("#username").focus()
        }
      }
    })
  }

  get username() {
    return this.form.get("username");
  }

  get password() {
    return this.form.get("password");
  }

  get passwordConfirm() {
    return this.form.get("passwordConfirm");
  }
}
