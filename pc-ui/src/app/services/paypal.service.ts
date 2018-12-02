import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PaypalService {

  constructor(private http: HttpClient) { }

  prepare(requestBody){
    return this.http.post('/paypal/prepare', requestBody);
  }
  
}
