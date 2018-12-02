import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-enter-buyer-details',
  templateUrl: './enter-buyer-details.component.html',
  styleUrls: ['./enter-buyer-details.component.css']
})
export class EnterBuyerDetailsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  pay(cardNumber){
  	console.log(cardNumber);
  }

}
