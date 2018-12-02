import { PaypalService } from './../services/paypal.service';
import { Component, OnInit } from '@angular/core';

import { HttpClient } from "@angular/common/http";
import { Router } from '@angular/router';
import { CardService } from '../services/card.service';

@Component({
  selector: 'app-payment-type',
  templateUrl: './payment-type.component.html',
  styleUrls: ['./payment-type.component.css']
})
export class PaymentTypeComponent implements OnInit {

  constructor(private http: HttpClient,
  	private router: Router,
    private card_service: CardService,
    private paypalService: PaypalService) { }

  ngOnInit() {
  }
  //payment_types: any;
  payment_types = [ {name: "Credit Card", image: "https://png2.kisspng.com/20180410/jkw/kisspng-credit-card-balance-transfer-credit-card-balance-t-credit-card-5acd51316ac8b1.5522821715234051054374.png" }, 
                    {name: "PayPal", image: "https://yt3.ggpht.com/a-/AN66SAzETZ0qdNMqaKxIYRua6DYCPY6TSMeyckHnAA=s900-mo-c-c0xffffffff-rj-k-no"},
                    {name: "Bitcoin", image: "http://mrjamie.cc/wp-content/uploads/2013/10/bitcoin-logo-1000.jpg" }]
                   

  getPaymentTypes(){
  	this.http.get('/getPaymentTypes').subscribe(data => {
  		if(data != null){
  			this.payment_types = data as any[];
  		}
  	})
  }

  sendRequest(type){
    if(type == 'Credit Card'){
      this.card_service.sendRequest();
    } else if (type == 'PayPal'){
      var mockData = {
        'amount': '5',
        'currency': 'USD',
        'payee': 'marko.krajinovic1233-facilitator@gmail.com'
      }
      this.paypalService.prepare(mockData).subscribe((data: any) => {
        //this.router.navigateByUrl(data.value);
        window.location.href = data.value;
      })
    } else if (type == 'Bitcoin'){

    }
  }

}
