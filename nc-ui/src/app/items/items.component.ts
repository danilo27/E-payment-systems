import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders, } from "@angular/common/http";
import { AuthenticationService } from './../services/authentication/authentication.service';
import * as FileSaver from "file-saver";
import 'rxjs/Rx' ;
import { Observable } from 'rxjs';
@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css'],
  providers: [AuthenticationService]
})
export class ItemsComponent implements OnInit {
  items = [];
  constructor(private authenticationService: AuthenticationService,private http: HttpClient) { }

  ngOnInit() {
  	this.http.get("api/nc/items/"+localStorage.getItem('username')).subscribe(data=>{
  		this.items = data as any[];
      console.log('items: ', this.items);
  	})
  }

  download(itemUrl){
    var dto = {
      name: 'url',
      value: itemUrl
    }
    console.log('dto', dto);
  

 

        
//                 let headers = new HttpHeaders();
// headers = headers.set('Accept', 'application/pdf');
//   this.http.get("api/nc/file/"+itemUrl, { headers: headers, responseType: 'blob' }).subscribe(data=>console.log('ok'))
 
this.getBlobThumbnail("api/nc/file",dto).subscribe(data=>{
  console.log('ok: ' + data);
  const blob = new Blob([data], { type: 'application/pdf' });
  const url= window.URL.createObjectURL(blob);
  window.open(url);
})
  }
 
 downloadFile(data: Response) {
  const blob = new Blob([data], { type: 'application/pdf' });
  const url= window.URL.createObjectURL(blob);
  window.open(url);
}
 

thumbnailFetchUrl : string = "https://south/generateThumbnail?width=100&height=100";
getBlobThumbnail(url,dto): Observable<Blob> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/pdf'      
    });
    return this.http.post<Blob>(url,dto
      , {headers: headers, responseType: 'blob' as 'json' });
  }
 

}
