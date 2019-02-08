import { PaypalService } from './../services/paypal.service';
import { Component, OnInit } from '@angular/core';

import { HttpClient } from "@angular/common/http";
import {Router, ActivatedRoute, Params} from '@angular/router';
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
    private paypalService: PaypalService,
    private activatedRoute: ActivatedRoute) { }

  token:string;
  cart: any;
  private supportedPaymentTypes = [];

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
        this.token = params['t'];
        console.log(this.token);
        this.http.get('/api/payment-types/'+this.token).subscribe(data=>{
          this.supportedPaymentTypes = data as any[];
          console.log('supportedPaymentTypes: ', this.supportedPaymentTypes);
        })
      });
  }
 
  sendRequest(paymantName) {
      this.card_service.sendRequest(this.token, paymantName); //token == id cart-a u pc-u
  }

  subscription(){
    
  }

}
