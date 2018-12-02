import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClient, HttpClientModule  } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PaymentTypeComponent } from './payment-type/payment-type.component';
import { AppRoutingModule } from './app-routing.module';
import { CardPaymentSuccessComponent } from './card-payment-success/card-payment-success.component';
import { CardPaymentFailedComponent } from './card-payment-failed/card-payment-failed.component';
import { CardPaymentErrorComponent } from './card-payment-error/card-payment-error.component';


@NgModule({
  declarations: [
    AppComponent,
    PaymentTypeComponent,
    CardPaymentSuccessComponent,
    CardPaymentFailedComponent,
    CardPaymentErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
