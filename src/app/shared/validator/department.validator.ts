import { Department } from './../model/department';
import { DepartmentService } from 'src/app/shared/services/department.service';
import { Observable, of } from 'rxjs';
import { map } from "rxjs/operators";
import { timer } from 'rxjs';
import { switchMap, catchError } from 'rxjs/operators';

import { AbstractControl, AsyncValidatorFn, ValidationErrors } from "@angular/forms";

export class DepartmentValidator {

  static isDuplicateDepartmentName(depratmentService: DepartmentService): AsyncValidatorFn {
    return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
      return timer(500).pipe(
        switchMap(() => depratmentService.getByDepartmentName(control.value).pipe(
            map((department: Department) => {
              return department != null ? { "duplicateDepartmentName": true } : null;
            }),
            catchError(err =>  of(null))
          ),
        )
      );
    };
  }
}
