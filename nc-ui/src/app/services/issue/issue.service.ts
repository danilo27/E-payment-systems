import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable()
export class IssueService {

  constructor(private http: HttpClient) { }

  getOne(id){
  	 return this.http.get('/nc/issue/getOne/'+id) as Observable<any>;
  }
}
