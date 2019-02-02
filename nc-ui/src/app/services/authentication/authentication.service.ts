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
    if (localStorage.getItem('token') != null) {
      return true;
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
