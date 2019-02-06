import { AuthenticationService } from './services/authentication/authentication.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import * as decode from 'jwt-decode';

@Injectable()
export class RoleGuardGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthenticationService){}

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const tokenPayload: any = decode(localStorage.getItem('token'));

    if (!this.authService.isAuthenticated() ||
      tokenPayload.role !== route.data.expectedRole) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
  
}
