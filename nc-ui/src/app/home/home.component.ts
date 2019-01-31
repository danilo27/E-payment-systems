import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../services/magazine/magazine.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private magazineService: MagazineService) { }

  magazines = [];


  ngOnInit() {
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
