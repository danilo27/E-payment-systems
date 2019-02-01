import { SupportedPaymentsService } from './../../services/supported-payments/supported-payments.service';
import { MagazineService } from './../../services/magazine/magazine.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-merchant',
  templateUrl: './new-merchant.component.html',
  styleUrls: ['./new-merchant.component.css'],
  providers: [MagazineService, SupportedPaymentsService]
})
export class NewMerchantComponent implements OnInit {

  private magazines;
  private supportedPayments;
  private selectedMagazineIssn;
  private selectedPayments: [];

  constructor(private magazineService: MagazineService, 
              private supportedPaymentsService: SupportedPaymentsService) { }

  ngOnInit() {
    //findAllSupportedPayments
    this.supportedPaymentsService.all().subscribe(data => {
      this.supportedPayments = data as any[];
      console.log(this.supportedPayments);
    })

    //findAllMagazines
    this.magazineService.all().subscribe(data => {
      this.magazines = data as any[];
      console.log(this.magazines);
    })
  }

  sendData(){
    alert(this.selectedMagazineIssn);
  }

}
