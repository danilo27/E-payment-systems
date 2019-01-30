import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router } from '@angular/router';
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
    private issueService: IssueService
    ) { }

  issue: any;
  ngOnInit() {
  	

  }

}
