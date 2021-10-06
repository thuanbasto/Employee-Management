import { DataService } from './data.service';
import { URL_BACKEND } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService extends DataService {

  constructor(http: HttpClient) {
    super(URL_BACKEND +  "/api/files", http);
  }

}
