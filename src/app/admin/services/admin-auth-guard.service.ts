import { AuthService } from './../../shared/services/auth.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate{

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let check = true;
    await this.authService.isAdmin().toPromise()
      .then(res => {
        console.log(res);
        check = true;
      })
      .catch(err => {
        console.log(err);
        check = false;
      })
    // if (this.authService.currentUser.roles.includes("ROLE_ADMIN")) return true;

    if (check) {
      return true;
    }
    this.router.navigate(["/no-access"]);
    return false;
  }
}
