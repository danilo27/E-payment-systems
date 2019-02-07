import { CardPaymentSuccessComponent } from './card-payment-success/card-payment-success.component';
import { CardPaymentFailedComponent } from './card-payment-failed/card-payment-failed.component';
import { CardPaymentErrorComponent } from './card-payment-error/card-payment-error.component';

import { PaypalSuccessSubComponent } from './paypal/paypal-success-sub/paypal-success-sub.component';
import { PaypalErrorComponent } from './paypal/paypal-error/paypal-error.component';
import { PaypalCancelComponent } from './paypal/paypal-cancel/paypal-cancel.component';
import { PaypalSuccessComponent } from './paypal/paypal-success/paypal-success.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PaymentTypeComponent } from './payment-type/payment-type.component';
import { BitcoinSuccessComponent } from './bitcoin/bitcoin-success/bitcoin-success.component';
import { BitcoinErrorComponent } from './bitcoin/bitcoin-error/bitcoin-error.component';

const routes: Routes = [
	{
		path:'',
		component: PaymentTypeComponent,
		pathMatch: 'full'
  },
  { path: 'payment-card-success', component: CardPaymentSuccessComponent },
  { path: 'payment-card-failed', component: CardPaymentFailedComponent },
  { path: 'payment-card-error', component: CardPaymentErrorComponent },
  { path: 'paypal-success', component: PaypalSuccessComponent },
  { path: 'paypal-success-sub', component: PaypalSuccessSubComponent },
  { path: 'paypal-cancel', component: PaypalCancelComponent },
  { path: 'paypal-error', component: PaypalErrorComponent },
  { path: 'bitcoin-success', component: BitcoinSuccessComponent },
  { path: 'bitcoin-error', component: BitcoinErrorComponent }
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})

export class AppRoutingModule { }
