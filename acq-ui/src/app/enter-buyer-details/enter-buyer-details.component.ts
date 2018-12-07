import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-enter-buyer-details',
  templateUrl: './enter-buyer-details.component.html',
  styleUrls: ['./enter-buyer-details.component.css']
})
export class EnterBuyerDetailsComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, 
    private activatedRoute: ActivatedRoute) { }
  token: string;
  ngOnInit() {
     this.activatedRoute.queryParams.subscribe(params => {
        this.token = params['t'];
        console.log(this.token);
      });
  }

  pay(cardNumber,cardHolderName,month,year,cv){
  	console.log(cardNumber,cardHolderName,month,year,cv);
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
	  		  if(data['value']==='')
  				  window.location.href = 'failed';
          else
            window.location.href = data['value'];
  			} else {
  				alert('Error occured');
  			}
  		
  		})
  	}
}


