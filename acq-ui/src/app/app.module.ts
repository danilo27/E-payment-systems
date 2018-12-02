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

@NgModule({
  declarations: [
    AppComponent,
    EnterBuyerDetailsComponent,
    NavbarComponent,
    RegisterNewAccountComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent
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
