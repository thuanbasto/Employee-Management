import { URL_IMAGE } from './../../../../environments/environment';
import { EmployeeService } from './../../../shared/services/employee.service';
import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  employees: any = [];
  size: number = 12;
  page: number = 1;
  pageTotal: number;
  numberTotal: number;
  search: string = "";
  sortBy: string = "employee_id";
  sortDirection: string = "DESC";
  URL_IMAGE = URL_IMAGE;

  constructor(
    private employeeService: EmployeeService,
  ) { }

  ngOnInit() {
    this.searchEmployee();
    $(".numberEachPage").css('color', 'white')

    $(document).ready(function () {
      $("#div-size").children().hide()
    });
  }

  searchEmployee(obj?: any) {
    let key = typeof obj;

    switch (key) {
      case "object":
        this.page = obj.page;
        this.size = obj.size;
        break;
      case "boolean":
        break;
      default:
        this.page = 1;
        break;
    }

    let search = this.search.trim();

    this.employeeService.pagingBySearch(search, this.page, this.size, this.sortBy, this.sortDirection).subscribe(
      response => this.employees = response
    )

    this.employeeService.numberTotalBySearch(search).subscribe(
      response => {
        this.numberTotal = response as number;
        this.pageTotal = Math.ceil(this.numberTotal/this.size);
      }
    )

  }
}
