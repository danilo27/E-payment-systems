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

  ngOnInit() {
      this.magazineService.all().subscribe(data => {
        this.magazines = data as any[];
        console.log(this.magazines);
      })
  }



  subscribe(magazine) {
    this.supportedPaymentsService.getByMagazineIssn(magazine.issn).subscribe(data => {
      if(data == null)
        alert('Subscription not supported')
      else{
        console.log(data);
      }

    })
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
}
