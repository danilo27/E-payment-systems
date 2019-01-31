import { AuthenticationService } from './../services/authentication/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {

  private token;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  submit(username, password) {
    this.authenticationService.login(username, password).subscribe((data: any) => {
      if (data) {
        this.token = data.accessToken;
        localStorage.setItem("token", this.token);
        localStorage.setItem("username", username);
        this.router.navigate(['/']);
      }
    },
      error => {
        alert("Pogresni kredencijali");
      });
  }

}
