import { AuthService } from './../services/auth.service';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(
      private router: Router,
      private authService: AuthService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      if (this.authService.isLoggedIn()) {
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          }
        });
      }

      return next.handle(request).pipe(
        catchError((error: any) => {
          // console.log(error);

          if (error.status === 403) {
            this.router.navigate(["/no-access"])
          }
          return throwError(error);
       })
      )
    }
}
