import { AuthenticationService } from './../services/authentication/authentication.service';
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
  providers: [AuthenticationService]
})
export class HomeComponent implements OnInit {

  constructor(private magazineService: MagazineService,
    private authenticationService: AuthenticationService,
    private transactionService: TransactionService,
    private router: Router) { }

  magazines = [];
  map: IHash = {};

  ngOnInit() {
    if (!this.authenticationService.isAuthenticated())
      this.router.navigate(['login']);
    else {
      this.magazineService.all().subscribe(data => {
        this.magazines = data as any[];
        console.log(this.magazines);
      })
    }
  }



  subscribe(magazine) {

  }

  buyIssue(issue) {


    this.map["itemType"] = "issue";
    this.map["itemId"] = issue.id;
    this.map["merchantId"] = "daniloMerchant";
    this.map["merchantPas"] = "pas";
    this.map["username"] = localStorage.getItem("username");

    var dto = {
      totalPrice: issue.price,
      itemDetails: this.map

    }

    console.log('transaction: ', dto);

    //sessionStorage.setItem('cart', JSON.stringify(dto));

    this.transactionService.proceedToPc(dto).subscribe(data => {
      window.location.href = data['value'];
    })

  }

  buyArticle(article) {

  }
}
