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

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
        this.token = params['t'];
        console.log(this.token);
        // this.http.get('/api/pc/getCart/'+this.token).subscribe(data=>{
        //   this.cart = data;
        //   console.log('cart: ', this.cart);
        // })

        //todo dobaviti nacine placanja preko merchantId-a

      });
  }
  //payment_types: any;
  payment_types = [{ name: "Credit Card", image: "https://farmaciaproderma.com/wp-content/uploads/2018/08/visa-mastercard-logo.jpg" },
  { name: "PayPal", image: "https://yt3.ggpht.com/a-/AN66SAzETZ0qdNMqaKxIYRua6DYCPY6TSMeyckHnAA=s900-mo-c-c0xffffffff-rj-k-no" },
  { name: "Bitcoin", image: "http://mrjamie.cc/wp-content/uploads/2013/10/bitcoin-logo-1000.jpg" }]


  getPaymentTypes() {
    this.http.get('/getPaymentTypes').subscribe(data => {
      if (data != null) {
        this.payment_types = data as any[];
      }
    })
  }

  sendRequest(type) {
    if (type == 'Credit Card') {
      this.card_service.sendRequest(this.token); //token == id cart-a u pc-u
    } else if (type == 'PayPal') {
      var mockData = {
        'amount': '5',
        'currency': 'USD',
        'merchantId': 'AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o',
        'merchantPassword': 'EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr'
      }
      this.paypalService.prepare(mockData).subscribe((data: any) => {
        //this.router.navigateByUrl(data.value);
        window.location.href = data.value;
      })
    } else if (type == 'Bitcoin') {
      window.location.href = 'http://localhost:8080/api/bitcoin/prepare'
    }
  }

  subscription(){
    var mockData = {
      'merchantId': 'AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o',
      'merchantPassword': 'EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr',
      'planName': 'T-Shirt of the Month Club Plan',
      'planDescription': 'Template creation.',
      'frequency': 'MONTH',
      'frequencyInterval': '1',
      'cycles': '12',
      'currency': 'USD',
      'amount': '20',
      'shippingAddress': '111 First Street',
      'stateCode': 'CA',
      'countryCode': 'US',
      'postalCode': '95070',
      'city': 'Saratoga'

    }
    this.paypalService.prepareSubscription(mockData).subscribe((data: any) => {
      console.log(data.value);
      window.location.href = data.value;
      //this.router.navigateByUrl(data.redirect_url);
    });
  }

}
