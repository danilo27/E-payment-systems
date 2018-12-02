import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router } from '@angular/router';
@Component({
  selector: 'app-enter-buyer-details',
  templateUrl: './enter-buyer-details.component.html',
  styleUrls: ['./enter-buyer-details.component.css']
})
export class EnterBuyerDetailsComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  pay(cardNumber,cardHolderName,month,year,cv){
  	console.log(cardNumber,cardHolderName,month,year,cv);
  	var card = {
  		pan: cardNumber,
  		securityCode: cv,
  		cardHolderName: cardHolderName,
  		expiringDate: month+'-'+year
   	}
  	this.http.post('/acqBank/validateAndExecute/tokenok', card).subscribe(data=>{
  			if(data!=null){
	  			console.log('ok');
	  			console.log(data);
	  		  
  				window.location.href = data['value'];
  			} else {
  				alert('Error occured');
  			}
  		
  		})
  	}
}


