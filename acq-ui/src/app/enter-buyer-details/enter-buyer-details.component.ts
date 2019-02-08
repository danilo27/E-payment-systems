import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Router, ActivatedRoute, Params} from '@angular/router';
 import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-enter-buyer-details',
  templateUrl: './enter-buyer-details.component.html',
  styleUrls: ['./enter-buyer-details.component.css']
})
export class EnterBuyerDetailsComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, 
    private activatedRoute: ActivatedRoute, private notifier: NotifierService) { }
  token: string;
  cart = {
    totalPrice: 0
  }
  ngOnInit() {
     
     this.activatedRoute.queryParams.subscribe(params => {
        this.token = params['t'];
        console.log(this.token);
        this.http.get('/acqBank/getCart/'+this.token).subscribe(data=>{
          this.cart = data as any;
          console.log('cart: ', this.cart);
        })
      });
  }

  pay(cardNumber,cardHolderName,month,year,cv){
  	console.log(cardNumber,cardHolderName,month,year,cv);
    if(cardNumber!=='' && cardHolderName!=='' && month!=='' && year!=='' && cv!==''){
  	var card = {
  		pan: cardNumber,
  		securityCode: cv,
  		cardHolderName: cardHolderName,
  		expiringDate: month+'-'+year
   	}
  	this.http.post('/acqBank/validateAndExecute/'+this.token, card).subscribe(data=>{
  			if(data!=null){
	  			console.log('ok');
	  			console.log(data);
	  		  if(data['itemDetails']['status']==='success')
  				  window.location.href = data['itemDetails']['successUrl'];
          else if(data['itemDetails']['status']==='failed')
            window.location.href = data['itemDetails']['failedUrl'];
          else 
            window.location.href = data['itemDetails']['errorUrl'];
  			} else {
  				alert('Error occured'); 
  			}
  		
  		})
  	} else {
      this.notifier.notify('error', 'Error while submiting. Please check the details.');
    }
}


}