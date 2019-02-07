import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PaypalService {

  constructor(private http: HttpClient) { }

  prepare(requestBody){
    return this.http.post('/api/payment/prepare/Paypal', requestBody);
  }

  confirm(requestBody){
    return this.http.post('/api/payment/confirm/Paypal', requestBody);
  }

  prepareSubscription(requestBody, paymentName){
    return this.http.post('/api/payment/prepare/subscription/' + paymentName, requestBody);
  }

  confirmSubscription(requestBody, paymentName){
    return this.http.post('/api/payment/confirm/subscription/' + paymentName, requestBody);
  }
  
}
