import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { SharedModule } from './../shared/shared.module';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { NgModule } from '@angular/core';
import { AdminNavbarComponent } from './components/admin/admin-navbar/admin-navbar.component';
import { AdminMenuComponent } from './components/admin/admin-menu/admin-menu.component';
import { AdminComponent } from './components/admin/admin-layout/admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeAddComponent } from './components/employee/employee-add/employee-add.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { EmployeeDetailComponent } from './components/employee/employee-detail/employee-detail.component';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    EmployeeListComponent,
    EmployeeAddComponent,
    AdminNavbarComponent,
    AdminMenuComponent,
    AdminComponent,
    EmployeeAddComponent,
    AdminDashboardComponent,
    EmployeeDetailComponent,
    DepartmentListComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    NgbModule
  ],
  exports: [
    EmployeeDetailComponent
  ],
  providers: []
})
export class AdminModule { }
