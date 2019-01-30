import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router, ActivatedRoute } from '@angular/router';
import { MagazineService } from '../services/magazine/magazine.service';
import { IssueService } from '../services/issue/issue.service';
 

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  constructor(private magazineService: MagazineService) {
  	this.magazineService.all().subscribe(data => {
  		this.magazines = data as any[]; 
  		console.log(this.magazines);
  	})
   }

   magazines = [];
   magazine = null;


  ngOnInit() {
  	  
  }

}
