import { AuthenticationService } from './../services/authentication/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
/*
  private logged = false;
*/
  constructor(private authService: AuthenticationService) { }

  ngOnInit() {/*
    if (this.authService.isAuthenticated()) {
      this.logged = true;
    } else {
      this.logged = false;
    }*/
  }

  isAuthenticated(){
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
  }

}
