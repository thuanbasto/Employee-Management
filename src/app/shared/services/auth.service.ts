import { User } from './../model/user';
import { map } from 'rxjs/operators';
import { URL_BACKEND } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private router: Router,
    private http: HttpClient) { }

  ngOnInit() {
  }

  login(credentials) {
    return this.http.post(URL_BACKEND + "/api/auth/signin", credentials)
      .pipe(
        map(
          (res : User) => {
            console.log(res)
            localStorage.setItem("token", res.accessToken);
            return true;
        })
      )
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(['/login']);
  }

  isLoggedIn(){
    let jwtHelper = new JwtHelperService();
    let token = localStorage.getItem("token");

    if (!token) {
      return false;
    }

    let expirationDate = jwtHelper.getTokenExpirationDate(token);
    let isExpired = jwtHelper.isTokenExpired(token);

    // console.log(expirationDate);
    // console.log(isExpired);

    return !isExpired;
  }

  get currentUser() : User {
    let token = localStorage.getItem("token");
    if (!token) return null;

    return new JwtHelperService().decodeToken(token);
  }

  register(user) {
    return this.http.post(URL_BACKEND + "/api/auth/signup", user);
  }

  isAdmin() {
    return this.http.get(URL_BACKEND + "/api/auth/isAdmin");
  }
}
