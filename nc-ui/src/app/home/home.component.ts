import { SupportedPaymentsService } from './../services/supported-payments/supported-payments.service';
import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../services/magazine/magazine.service';
import { Router } from '@angular/router';
import { TransactionService } from '../services/transaction.service';

export interface IHash {
  [details: string]: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [SupportedPaymentsService]
})
export class HomeComponent implements OnInit {

  constructor(private magazineService: MagazineService,
    private supportedPaymentsService: SupportedPaymentsService,
    private transactionService: TransactionService,
    private router: Router) { }

  magazines = [];
  map: IHash = {};
  username = null;

  ngOnInit() {
    this.username = localStorage.getItem('username');
    this.magazineService.all().subscribe(data => {
      this.magazines = data as any[];
      console.log(this.magazines);
    })
  }



  subscribe(magazine) {
    this.supportedPaymentsService.getByMagazineIssn(magazine.issn).subscribe((data: Array<any>) => {
      if (data == null) {
        alert('Subscription not supported')
        return;
      } else {
        var paypalFound = false;
        data.forEach(item => {
          if (item.name == 'Paypal')
            paypalFound = true;
        });
        if (!paypalFound){
          alert('Subscription not supported')
          return;
        }else {
          var mockData = this.prepareSubscription();

          this.transactionService.sendSubscription(mockData).subscribe(data => {
            window.location.href = data.redirectUrl;
          });
        }
      }

    })
  }

  prepareSubscription(){
    var mockData = {
      'paypalApiKey': 'AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o',
      'paypalApiPassword': 'EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr',
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
    return mockData;
  }

  buyIssue(magazine, issue) {
    this.map = {};

    this.map["itemType"] = "issue";
    this.map["itemId"] = issue.id;
    this.map["merchantId"] = magazine.issn;
    //this.map["merchantPas"] = magazine.merchant.merchantPass;
    this.map["username"] = localStorage.getItem("username");

    var dto = {
      totalPrice: issue.price,
      itemDetails: this.map

    }

    console.log('transaction: ', dto);
    console.log('itemId', issue.id)


    this.transactionService.proceedToPc(dto).subscribe(data => {
      window.location.href = data['value'];
    })

  }

  buyArticle(magazine, article) {
    this.map = {};

    this.map["itemType"] = "article";
    this.map["itemId"] = article.id;
    this.map["merchantId"] = magazine.issn;
    //this.map["merchantPas"] = magazine.merchant.merchantPass;
    this.map["username"] = localStorage.getItem("username");

    var dto = {
      totalPrice: article.price,
      itemDetails: this.map

    }

    console.log('transaction: ', dto);
    console.log('itemId', article.id)


    this.transactionService.proceedToPc(dto).subscribe(data => {
      window.location.href = data['value'];
    })
  }

  pay(magazine, article){
    this.map = {};
    this.map["itemType"] = "subscription";
    this.map["itemId"] = article.id;
    this.map["merchantId"] = magazine.issn; 
    this.map["username"] = localStorage.getItem("username");

    var dto = {
      totalPrice: "43.0",
      itemDetails: this.map

    }

    console.log('transaction: ', dto);
    console.log('itemId', article.id)


    this.transactionService.proceedToPc(dto).subscribe(data => {
      window.location.href = data['value'];
    })
  }
}
