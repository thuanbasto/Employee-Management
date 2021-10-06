import { AdminAuthGuard } from './services/admin-auth-guard.service';
import { AuthGuard } from './../shared/services/auth-guard.service';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { EmployeeDetailComponent } from './components/employee/employee-detail/employee-detail.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { EmployeeAddComponent } from './components/employee/employee-add/employee-add.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { AdminComponent } from './components/admin/admin-layout/admin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    canActivate: [AuthGuard, AdminAuthGuard],
    children: [
      { path: '', component: AdminDashboardComponent},
      { path: 'employee', component: EmployeeListComponent },
      { path: 'employee-detail/:id', component: EmployeeDetailComponent },
      { path: 'employee-add', component: EmployeeAddComponent },
      { path: 'employee-add/:id', component: EmployeeAddComponent },
      { path: 'department', component: DepartmentListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
