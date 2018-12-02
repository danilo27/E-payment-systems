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
  { path: 'paypal-success', component: PaypalSuccessComponent }
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
