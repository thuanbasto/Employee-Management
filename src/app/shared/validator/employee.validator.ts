import { FormValidator } from './form.validator';
import { PATTERN_REGEX } from './../model/pattern-regex';
import { AbstractControl, ValidationErrors } from "@angular/forms";

export class EmployeeValidator {
	static isPhone(control: AbstractControl): ValidationErrors | null {
		if (control.value == null || !(control.value as string).match(PATTERN_REGEX.isPhone)) {
			return { phone: true }
		}
		return null;
  }

  static isBirthday(control: AbstractControl): ValidationErrors | null {
    let currentDate = new Date();
    currentDate.setHours(0,0,0,0);
    let birthday = new Date(control.value);
    if (currentDate > birthday && (currentDate.getFullYear() - birthday.getFullYear() < 110)) {
      return null;
    }
    return { birthday: true }
	}

	static isName(control: AbstractControl): ValidationErrors | null {
		let name = FormValidator.removeAscent(control.value as string);
		if (name == null || !name.match(PATTERN_REGEX.isName) || name.length > 100) {
			return { name: true }
		}
		return null;
  }
}
