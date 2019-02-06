import { JwtHelper } from 'angular2-jwt';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as decode from 'jwt-decode';


@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient, private router: Router) { }

  public login(username, password){
    var request: any = {};
    request.username = username;
    request.password = password;

    return this.http.post("/api/auth/login", request);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    if (token != null) {
      const jwtHelper: JwtHelper = new JwtHelper();
      if (jwtHelper.isTokenExpired(token)){
        localStorage.clear();
      }
      return (!jwtHelper.isTokenExpired(token));
    }
    return false;
  }

  public getRole() {
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    //console.log(tokenPayload);
    return tokenPayload['role'];
  }

  public logout(){
    localStorage.clear();
    //localStorage.removeItem('token');
    this.router.navigate(['/login']);
    //window.location.reload();
    //return this.http.get("/booking/auth/logout");  
  }


}
