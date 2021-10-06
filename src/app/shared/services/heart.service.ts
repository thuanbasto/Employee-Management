import { DataService } from './data.service';
import { URL_BACKEND } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeartService extends DataService {

  constructor(http: HttpClient) {
    super(URL_BACKEND +  "/api/hearts", http);
  }

  checkIsHearted(user_id, employee_id) {
    return this.http.get(this.url, {
      params: {
        user_id : user_id,
        employee_id : employee_id
      }
    });
  }

  countHeart(employee_id){
    return this.http.get(this.url + "/count/" + employee_id);
  }
}
