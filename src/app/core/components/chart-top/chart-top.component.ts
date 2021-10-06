import { URL_IMAGE, URL_IMAGE_DEFAULT } from './../../../../environments/environment';
import { EmployeeHeart } from './../../../shared/model/employee-heart';
import { EmployeeService } from './../../../shared/services/employee.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chart-top',
  templateUrl: './chart-top.component.html',
  styleUrls: ['./chart-top.component.css']
})
export class ChartTopComponent implements OnInit {

  employees : EmployeeHeart[] = [];

  constructor(
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    this.getTopEmployees();
  }

  getTopEmployees() {
    this.employeeService.getTopEmployees().subscribe(
      (res : any) => {
        console.log(res);
        res.forEach((employee: EmployeeHeart) => {
          employee.imageUrl = employee.imageUrl ? URL_IMAGE + "/" + employee.imageUrl : URL_IMAGE_DEFAULT;
          this.employees.push(employee)
        })
      },
      err => {
        console.log(err);
      }
    )
  }
}
