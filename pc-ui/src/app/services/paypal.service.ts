import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PaypalService {

  constructor(private http: HttpClient) { }

  prepare(requestBody){
    return this.http.post('/api/paypal/prepare', requestBody);
  }

  confirm(requestBody){
    return this.http.post('/api/paypal/confirm', requestBody);
  }

  prepareSubscription(requestBody){
    return this.http.post('/api/paypal/prepare/subscription', requestBody);
  }

  confirmSubscription(requestBody){
    return this.http.post('/api/paypal/confirm/subscription', requestBody);
  }
  
}
