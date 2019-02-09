import { MerchantService } from './../../services/merchant/merchant.service';
import { SupportedPaymentsService } from './../../services/supported-payments/supported-payments.service';
import { MagazineService } from './../../services/magazine/magazine.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
 import { GetValuesPipe } from '../../pip.pipe';

export interface IHash{

}

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
  private cardMerchantId: string;
  private cardMerchantPassword: string;
  private selectedBankUrl: string;
  private dynamicForms: IHash = {};  
  private pipeForm = [];
  private successUrl = "http://localhost:4204/#/success";
  private failedUrl = "http://localhost:4204/#/failed";
  private errorUrl = "http://localhost:4204/#/error";

  constructor(private magazineService: MagazineService, 
              private supportedPaymentsService: SupportedPaymentsService,
              private merchantService: MerchantService,
              private http: HttpClient) {}

  ngOnInit() {
    this.supportedPaymentsService.all().subscribe(data => {
      this.supportedPayments = data as any[];
      console.log(this.supportedPayments);
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
  
  paymentTypeFields: IHash = {}; 
  innerMapFields: IHash = {};

  sendData(value, form){
    
 
    for(var type in this.types){
      console.log('typename ', this.types[type]['name'])
      this.innerMapFields = {}; 
      for (var property in value) {
        let payTypeKey = property.split('-')[0];
        let fieldKey = property.split('-')[1];

        if(this.types[type]['name'] == payTypeKey){
          this.innerMapFields[fieldKey] = value[property];
        }
      }
      this.paymentTypeFields[this.types[type]['name']] = this.innerMapFields;
    }

    console.log(this.paymentTypeFields);

 

    var paymentTypeFields = {

    }

    var request = {
      "magazineIssn": this.selectedMagazineIssn,
      "supportedPaymentsIds": this.checkedSupportedPayments,
       paymentTypeFields: this.paymentTypeFields,
       merchantId: this.selectedMagazineIssn,
       successUrl: this.successUrl,
       failedUrl: this.failedUrl,
       errorUrl: this.errorUrl
    }

   console.log('request: ', request);
   this.merchantService.createMerchant(request).subscribe((data: any) => {
     alert(data.message);
    })
  }

  request(){
    var dto = {
      value: this.selectedBankUrl
    }
    console.log(dto)
    this.http.post('/api/merchant/request',dto).subscribe(data=>{
      this.cardMerchantId = data['merchantId'];
      this.cardMerchantPassword = data['merchantPassword'];
      console.log('credentials: ', this.cardMerchantId, this.cardMerchantPassword);
    })
  }

  objectKeys = Object.keys;
  types = [];

  checkSupportedPayment(sp){
    var index = this.containsElement(this.checkedSupportedPayments, sp);
    if (index == -1) {
      this.checkedSupportedPayments.push(sp.id);
      this.types.push(sp);
      console.log(this.types);
      if(this.dynamicForms[sp.name]==null){
        this.supportedPaymentsService.getFields(sp.name).subscribe(data => {

              this.dynamicForms[sp.name] = data as any[];
              // console.log('f: ', this.dynamicForms[sp.name] );
              this.pipeForm = GetValuesPipe.prototype.transform(this.dynamicForms[sp.name]);
             // console.log(this.pipeForm)
          })
      }
    } else {
      this.checkedSupportedPayments.splice(index, 1);
      this.types.splice(index, 1);
      console.log(this.types);
      if(this.dynamicForms[sp.name]!=null){
          delete this.dynamicForms[sp.name];
          console.log(this.dynamicForms)
      }
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

 
 