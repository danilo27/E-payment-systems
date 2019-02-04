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

  sendRequest(cartId, paymentName){
/*
  	var paymentRequest = {
  		// merchantId: cart["merchantId"],
  		// merchantPassword: cart["merchantPassword"],
  		// amount: cart.totalPrice,
  		// merchantOrderId: cart.id,
  		// successUrl: "",
  		// failedUrl: "",
  		// errorUrl: ""
      id: cartId //id cart-a u PC-u
  	}
		console.log('[pc] paymentRequest ', paymentRequest);*/
		console.log('[pc] paymentType ', paymentName);

		this.http.get('/api/pc/cart/' + cartId).subscribe((cart: any) => {
			cart.paypalApiKey = "AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o";
			cart.paypalApiPassword = "EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr";
			this.http.post('/api/payment/prepare/' + paymentName, cart).subscribe((data: any) => {
				if(data != null){
					 
					console.log(data);
				 
					if(data.value!==''){		//value = redirekt url
						window.location.href = data.value;
						//window.location.href = data['paymentUrl']+'?t='+data['paymentRequestToken'];
					} else {
						alert('Error occured');
					}
				
				}
			})
		});
		
  	

  }

  proceed(requestBody){
    return this.http.post('/api/card/proceed', requestBody);
  }

}
