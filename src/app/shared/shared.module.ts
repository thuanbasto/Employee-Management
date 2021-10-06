import { RouterModule } from '@angular/router';
import { JwtInterceptor } from './interceptor/jwt.interceptor';
import { AuthService } from './services/auth.service';
import { ErrorMessageComponent } from './components/error-message/error-message.component';
import { FileService } from './services/file.service';
import { DepartmentService } from './services/department.service';
import { EmployeeService } from './services/employee.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DataService } from './services/data.service';
import { NgModule } from '@angular/core';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ModalConfirmComponent } from './components/modal-confirm/modal-confirm.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { AgePipe } from './pipe/age.pipe';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  declarations: [
    NotFoundComponent,
    ModalConfirmComponent,
    PaginationComponent,
    ErrorMessageComponent,
    AgePipe,
    ForbiddenComponent
  ],
  imports: [
    RouterModule,
    FormsModule,
    CommonModule
  ],
  exports: [
    PaginationComponent,
    ModalConfirmComponent,
    ErrorMessageComponent,
    AgePipe,
  ],
  providers: [
    EmployeeService,
    DepartmentService,
    FileService,
    DataService,
    AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ]
})
export class SharedModule { }
