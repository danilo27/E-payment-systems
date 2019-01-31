import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable()
export class ArticleService {

 constructor(private http: HttpClient) { }

  all(){
  	 return this.http.get('/nc/magazine/all') as Observable<any>;
  }

  sendFile(file:any){
        let formData:FormData = new FormData();
        formData.append('file', file);
        return this.http.post("/nc/file/addFile",formData) as Observable<any>;
  }

  addArticle(obj){
        return this.http.post("/nc/file/addArticle", obj) as Observable<any>;
   }
}
