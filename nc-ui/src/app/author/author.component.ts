import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router, ActivatedRoute } from '@angular/router';
import { MagazineService } from '../services/magazine/magazine.service';
import { IssueService } from '../services/issue/issue.service';
import { ArticleService } from '../services/article/article.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  constructor(private magazineService: MagazineService,
    private articleService: ArticleService
    ) {
  	this.magazineService.all().subscribe(data => {
  		this.magazines = data as any[]; 
  		console.log(this.magazines);
  	})
   }

   magazines = [];
   magazine = null;


  ngOnInit() {
  	  
  }

 uploadFile(event) {
        let fileList: FileList = event.target.files;
        if(fileList.length > 0) {
            let file: File = fileList[0];
            this.articleService.sendFile(file).subscribe(x=>{
               console.log('success upload');
            });
        }
    }

  addArticle(title,fName,lName){
    var obj = {
      "name": title,
      "fName": fName,
      "lName": lName,
      "issue": this.magazine.issues[this.magazine.issues.length-1]
    }
    console.log('obj ',obj);
    this.articleService.addArticle(obj).subscribe(x=>{
               console.log('success add');
            });
  }

}
