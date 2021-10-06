import { ForbiddenComponent } from './shared/components/forbidden/forbidden.component';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
  },
  {
    path: '',
    loadChildren: () => import('./core/core.module').then(m => m.CoreModule),
  },
  { path: 'no-access', component: ForbiddenComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
