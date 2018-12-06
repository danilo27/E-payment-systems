import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CardService {
  base_url : string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }
  
  check(){
    var dto = {
    	name: "danilo",
    	pas: "pas"
    }

  	this.http.post('/api/checkDTO', dto).subscribe(data=>{
  		if(data!=null){
  			console.log('ok');
  		}
  	})

  }

  sendRequest(){

  	var paymentRequest = {
  		merchantId: "daniloMerchant",
  		merchantPassword: "pas",
  		amount: 320.0,
  		merchantOrderId: 1,
  		successUrl: "",
  		failedUrl: "",
  		errorUrl: ""
  	}

  	this.http.post('/api/card/prepare', paymentRequest).subscribe(data=>{
  		if(data!=null){
  			 
  			console.log(data);
  		 
  			if(data['paymentUrl']!==''){
  				window.location.href = data['paymentUrl']+'?t='+data['paymentRequestToken'];
  			} else {
  				alert('Error occured');
  			}
  		
  		}
  	})

  }

  proceed(requestBody){
    return this.http.post('/api/card/proceed', requestBody);
  }

}
