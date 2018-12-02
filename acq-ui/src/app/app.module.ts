import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { EnterBuyerDetailsComponent } from './enter-buyer-details/enter-buyer-details.component';
import { AppRoutingModule } from './/app-routing.module';

import { HttpClient, HttpClientModule  } from '@angular/common/http';

import { NgXCreditCardsModule } from 'ngx-credit-cards';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterNewAccountComponent } from './register-new-account/register-new-account.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { CardPaymentSuccessComponent } from './card-payment-success/card-payment-success.component';
import { CardPaymentFailedComponent } from './card-payment-failed/card-payment-failed.component';
import { CardPaymentErrorComponent } from './card-payment-error/card-payment-error.component';

@NgModule({
  declarations: [
    AppComponent,
    EnterBuyerDetailsComponent,
    NavbarComponent,
    RegisterNewAccountComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    CardPaymentSuccessComponent,
    CardPaymentFailedComponent,
    CardPaymentErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgXCreditCardsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
