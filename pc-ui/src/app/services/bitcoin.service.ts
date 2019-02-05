import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BitcoinService {

  constructor(private http: HttpClient) { }

  prepare(requestBody){
    return this.http.post('/api/bitcoin/prepare', requestBody);
  }

  confirm(requestBody){
    return this.http.post('/api/bitcoin/confirm', requestBody);
  }
  
}
