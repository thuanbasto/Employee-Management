import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(public url: string, public http: HttpClient) { }

  getAll() {
    return this.http.get(this.url);
  }

  get(id) {
    return this.http.get(this.url + `/${id}`);
  }

  update(id, obj) {
    return this.http.put(this.url + `/${id}`, obj);
  }

  create(obj) {
    return this.http.post(this.url, obj);
  }

  delete(id) {
    return this.http.delete(this.url + "/" + id);
  }

  deletes(ids) {
    return this.http.delete(this.url, {
      params: {
        ids : ids,
      }
    })
  }

  pagingBySearch(search, page, size, sortBy, sortDirection) {
    return this.http.get(this.url + "/paging", {
      params: {
        search : search,
        page : page,
        size : size,
        sortBy : sortBy,
        sortDirection : sortDirection
      }
    });
  }

  numberTotalBySearch(search) {
    search = "?search=" + search;
    return this.http.get(this.url + "/count" + search);
  }
}
