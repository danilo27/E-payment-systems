import { PaypalService } from './../../services/paypal.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-paypal-success',
  templateUrl: './paypal-success.component.html',
  styleUrls: ['./paypal-success.component.css']
})
export class PaypalSuccessComponent implements OnInit {

  constructor(private route: ActivatedRoute, private paypalService: PaypalService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(data => {
      this.paypalService.confirm({'paymentId': data.paymentId, 'payerId': data.PayerID})
      .subscribe((retData: any) => {
        alert(retData.successMessage);
      });
    });
  }

}
