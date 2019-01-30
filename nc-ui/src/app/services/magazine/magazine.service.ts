import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable()
export class MagazineService {

  constructor(private http: HttpClient) { }

  all(){
  	 return this.http.get('/nc/magazine/all') as Observable<any>;
  }
}
