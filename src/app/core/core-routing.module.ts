import { ChatComponent } from './components/chat/chat.component';
import { ChartTopComponent } from './components/chart-top/chart-top.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './../shared/services/auth-guard.service';
import { LoginComponent } from './components/login/login.component';
import { EmployeeDetailComponent } from '../admin/components/employee/employee-detail/employee-detail.component';
import { HomeComponent } from "./components/home/home.component";
import { CoreComponent } from "./components/core/core.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  {
    path: "",
    component: CoreComponent,
    children: [
      { path: "", component: HomeComponent },
      { path: "login", component: LoginComponent},
      { path: "register", component: RegisterComponent},
      { path: "top", component: ChartTopComponent},
      { path: "employee/:id", component: EmployeeDetailComponent },
      { path: "chat", component: ChatComponent }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoreRoutingModule {}
