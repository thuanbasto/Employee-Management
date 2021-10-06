import { FormValidator } from "./../../../shared/validator/form.validator";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { AuthService } from "./../../../shared/services/auth.service";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
declare var $: any;

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  invalidLogin: boolean;

  form = new FormGroup({
    username: new FormControl("", [Validators.required]),
    password: new FormControl("", [Validators.required]),
  });

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(["/"]);
    }
    $("#username").focus();
  }

  signIn() {
    let check = true;
    check = FormValidator.validForm(this.form);

    if (check) {
      this.authService.login(this.form.value).subscribe(
        (result) => {
          console.log(result);
          if (result) {
            let returnUrl = this.route.snapshot.queryParamMap.get("returnUrl");
            this.router.navigate([returnUrl || "/"]);
          } else {
            this.invalidLogin = true;
          }
        },
        (err) => {
          console.log(err);
          this.invalidLogin = true;
        }
      );
    } else {
      FormValidator.focusErrorField();
    }
  }

  get username() {
    return this.form.get("username");
  }

  get password() {
    return this.form.get("password");
  }
}
