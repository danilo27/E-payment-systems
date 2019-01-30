import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router, ActivatedRoute } from '@angular/router';
import { MagazineService } from '../services/magazine/magazine.service';
import { IssueService } from '../services/issue/issue.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  constructor(private http: HttpClient,
    private router: Router,
    private issueService: IssueService,
    private activeRoute: ActivatedRoute
    ) { }

  issue: any;
  id: any;
  ngOnInit() {
  	this.id = this.activeRoute.snapshot.params['id'];
  	this.issueService.getOne(this.id).subscribe(data => {
  		this.issue = data;
  		console.log(this.issue);
  	})

  }

}
