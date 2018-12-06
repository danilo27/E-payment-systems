import { PaypalErrorComponent } from './paypal/paypal-error/paypal-error.component';
import { PaypalCancelComponent } from './paypal/paypal-cancel/paypal-cancel.component';
import { PaypalSuccessComponent } from './paypal/paypal-success/paypal-success.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PaymentTypeComponent } from './payment-type/payment-type.component';

const routes: Routes = [
	{
		path:'',
		component: PaymentTypeComponent,
		pathMatch: 'full'
  },
  { path: 'paypal-success', component: PaypalSuccessComponent },
  { path: 'paypal-cancel', component: PaypalCancelComponent },
  { path: 'paypal-failed', component: PaypalErrorComponent },
  { path: 'bitcoin-success', component: PaypalSuccessComponent },
  { path: 'bitcoin-cancel', component: PaypalCancelComponent }
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
