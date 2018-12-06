import { PaypalService } from './../../services/paypal.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-paypal-success-sub',
  templateUrl: './paypal-success-sub.component.html',
  styleUrls: ['./paypal-success-sub.component.css']
})
export class PaypalSuccessSubComponent implements OnInit {

  constructor(private route: ActivatedRoute, private paypalService: PaypalService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(data => {
      this.paypalService.confirmSubscription({'token': data.token}).subscribe((retData: any) => {
        alert(retData.value);
      });
    });
  }

}
