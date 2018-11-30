import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PaymentTypeComponent } from './payment-type/payment-type.component';

const routes: Routes = [
	{
		path:'',
		component: PaymentTypeComponent,
		pathMatch: 'full'
	}
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
