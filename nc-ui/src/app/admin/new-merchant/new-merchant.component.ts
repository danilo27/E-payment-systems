import { MerchantService } from './../../services/merchant/merchant.service';
import { SupportedPaymentsService } from './../../services/supported-payments/supported-payments.service';
import { MagazineService } from './../../services/magazine/magazine.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-merchant',
  templateUrl: './new-merchant.component.html',
  styleUrls: ['./new-merchant.component.css'],
  providers: [MagazineService, SupportedPaymentsService, MerchantService]
})
export class NewMerchantComponent implements OnInit {

  private magazines;
  private supportedPayments;
  private selectedMagazineIssn;
  private checkedSupportedPayments: any[] = [];

  constructor(private magazineService: MagazineService, 
              private supportedPaymentsService: SupportedPaymentsService,
              private merchantService: MerchantService) {}

  ngOnInit() {
    this.supportedPaymentsService.all().subscribe(data => {
      this.supportedPayments = data as any[];
      //console.log(this.supportedPayments);
    })

    this.magazineService.all().subscribe(data => {
      this.magazines = data as any[];
      //console.log(this.magazines);
    })
  }

  sendData(){
    var request = {
      "magazineIssn": this.selectedMagazineIssn,
      "supportedPaymentsIds": this.checkedSupportedPayments
    }

    console.log(request);
    this.merchantService.createMerchant(request).subscribe((data: any) => {
      alert(data.message);
    })
  }

  

  checkSupportedPayment(sp){
    var index = this.containsElement(this.checkedSupportedPayments, sp);
    if (index == -1) {
      this.checkedSupportedPayments.push(sp.id);
    } else {
      this.checkedSupportedPayments.splice(index, 1);
    }
  }

  containsElement(list: any[], element) {
    var index = 0;
    for(let e of list){
      if(e == element.id)
        return index;
      index++;
    }
    return -1;
  }
}
