import { URL_BACKEND } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { DataService } from './data.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends DataService{

  constructor(http: HttpClient) {
    super(URL_BACKEND + "/api/employees", http);
  }

  getTopEmployees() {
    return this.http.get(this.url + "/top");
  }
}
