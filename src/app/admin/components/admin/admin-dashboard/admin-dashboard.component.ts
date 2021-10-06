import { DepartmentService } from '../../../../shared/services/department.service';
import { EmployeeService } from "../../../../shared/services/employee.service";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";

@Component({
  selector: "app-admin-dashboard",
  templateUrl: "./admin-dashboard.component.html",
  styleUrls: ["./admin-dashboard.component.css"],
})
export class AdminDashboardComponent implements OnInit {
  numberOfEmployee: any;
  numberOfDepartment: any;

  constructor(
    private employeeService: EmployeeService,
    private departmentService: DepartmentService
  ) {}

  ngOnInit() {
    this.employeeService.numberTotalBySearch("").subscribe(
      (data) => (this.numberOfEmployee = data),
      (err) => console.log(err)
    );
    this.departmentService.numberTotalBySearch("").subscribe(
      (data) => (this.numberOfDepartment = data),
      (err) => console.log(err)
    );
  }
}
