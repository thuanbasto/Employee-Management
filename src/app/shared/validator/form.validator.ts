import { FormGroup } from '@angular/forms';
export class FormValidator {
	static focusErrorField() {
    let invalidFields = [].slice.call(document.getElementsByClassName('ng-invalid form-control'));
    if (invalidFields.length > 0) {
		  invalidFields[0].focus();
    }
	}

	static setErrorForField(error: object, form: FormGroup) {
		if (error) {
			console.log(error);

			Object.keys(error).forEach(key => {
				form.get(key).setErrors({ [key] : error[key] })
      })
		}
	}

	static validForm(form: FormGroup) {
		let check = true;

		Object.keys(form.controls).forEach(key => {
			let control = form.controls[key];

			if (control instanceof FormGroup) {
				let check2 = this.validForm(control);
				if (check) {
					check = check2;
				}
			} else {
				if (control.invalid) {
					check = false;
					control.markAsTouched();
				}
			}
		})

		return check;
  }

  static removeAscent(str) {
		if (str === null || str === undefined) return str;
		str = str.toLowerCase();
		str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
		str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
		str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
		str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
		str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
		str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
		str = str.replace(/đ/g, "d");
		return str;
  }
}
