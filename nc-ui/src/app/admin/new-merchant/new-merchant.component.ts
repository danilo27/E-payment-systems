import { MerchantService } from './../../services/merchant/merchant.service';
import { SupportedPaymentsService } from './../../services/supported-payments/supported-payments.service';
import { MagazineService } from './../../services/magazine/magazine.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";

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
  private banks = [];
  private merchantId: string;
  private merchantPassword: string;
  private selectedBankUrl: string;

  constructor(private magazineService: MagazineService, 
              private supportedPaymentsService: SupportedPaymentsService,
              private merchantService: MerchantService,
              private http: HttpClient) {}

  ngOnInit() {
    this.supportedPaymentsService.all().subscribe(data => {
      this.supportedPayments = data as any[];
      //console.log(this.supportedPayments);
    })

    // this.magazineService.all().subscribe(data => {
    //   this.magazines = data as any[];
    //   //console.log(this.magazines);
    // })

     this.magazineService.withoutMerchant().subscribe(data => {
      this.magazines = data as any[];
      //console.log(this.magazines);
    })

   this.http.get('/api/merchant/getBanks').subscribe(data=>{
      this.banks = data as any[];
      console.log('banks: ', this.banks);
    })
      
    
  }

  sendData(){
    var request = {
      "magazineIssn": this.selectedMagazineIssn,
      "supportedPaymentsIds": this.checkedSupportedPayments,
      "merchantId": this.merchantId,
      "merchantPassword": this.merchantPassword,
      "merchantBankUrl": this.selectedBankUrl
    }

    console.log(request);
    this.merchantService.createMerchant(request).subscribe((data: any) => {
      alert(data.message);
    })
  }

  request(){
    var dto = {
      value: this.selectedBankUrl
    }
    this.http.post('/api/merchant/request',dto).subscribe(data=>{
      this.merchantId = data['merchantId'];
      this.merchantPassword = data['merchantPassword'];
      console.log('credentials: ', this.merchantId, this.merchantPassword);
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
