import { Component, OnInit, Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.css']
})
export class ErrorMessageComponent implements OnInit {

  @Input() control: AbstractControl;
  @Input() controlName: string;
  message: string;

  constructor() { }

  ngOnInit() {
  }

  create(control : AbstractControl, nameControl: string) {
    if (control.errors != null) {
      let error = control.errors;
      let message = "";
      let key = Object.keys(error)[0];
      switch (key) {
        case "required":
          message = `${nameControl} is required`;
          break;
        case "minlength":
          message = `${nameControl} should be minimum ${error.minlength.requiredLength} characters`
          break;
        case "maxlength":
          message = `${nameControl} should be maximun ${error.maxlength.requiredLength} characters`
          break;
        case "phone":
          message = `${nameControl} should be 9 - 10 numbers`
          break;
        case "name":
          message = `${nameControl} should be 2 - 100 characters`
          break;
        case "duplicateDepartmentName":
          message = `${nameControl} is existed`
          break;
        case "birthday":
          message = `${nameControl} should be less than current date and age less than 110`
          break;
        case "placeOfBirth":
          message = `${nameControl} should be 3 - 255 characters`
          break;
        default:
          message = error[key];
      }
      return message;
    }
  }

  ngDoCheck() {
    this.message = this.create(this.control, this.controlName);
  }
}
