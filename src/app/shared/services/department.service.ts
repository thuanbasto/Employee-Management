import { AuthService } from './auth.service';
import { URL_BACKEND } from './../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from './data.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService extends DataService{

  constructor(http: HttpClient, private authService: AuthService) {
    super(URL_BACKEND +  "/api/departments", http);
  }

  getByDepartmentName(name) {
    return this.http.get(this.url + `/${name}`);
  }

  // create(obj) {
  //     let headers = new HttpHeaders();
  //     headers.append("Authorization", "Bearer " + localStorage.getItem("token"));
  //     return this.http.post(this.url, obj, { headers: headers })
  // }
}
