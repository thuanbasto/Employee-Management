import { AngularFirestoreModule } from '@angular/fire/firestore';
import { SharedModule } from './../shared/shared.module';
import { AdminModule } from './../admin/admin.module';
import { CoreRoutingModule } from './core-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CoreComponent } from './components/core/core.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ChartTopComponent } from './components/chart-top/chart-top.component';
import { RegisterComponent } from './components/register/register.component';
import { ChatComponent } from './components/chat/chat.component';

@NgModule({
	declarations: [
		NavbarComponent,
		CoreComponent,
		HomeComponent,
		LoginComponent,
		ChartTopComponent,
		RegisterComponent,
		ChatComponent
	],
	imports: [
		CommonModule,
    CoreRoutingModule,
    AdminModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  providers:[
  ]
})
export class CoreModule { }
