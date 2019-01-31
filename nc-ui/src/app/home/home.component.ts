import { AuthenticationService } from './../services/authentication/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../services/magazine/magazine.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [AuthenticationService]
})
export class HomeComponent implements OnInit {

  constructor(private magazineService: MagazineService,
    private authenticationService: AuthenticationService,
    private router: Router) { }

  magazines = [];


  ngOnInit() {
    if (!this.authenticationService.isAuthenticated())
      this.router.navigate(['login']);

    this.magazineService.all().subscribe(data => {
      this.magazines = data as any[];
      console.log(this.magazines);
    })
  }

   

  subscribe(magazine){

  }

  buyIssue(issue){
    console.log('Buying issue: ', issue);
  }

  buyArticle(article){

  }
}
