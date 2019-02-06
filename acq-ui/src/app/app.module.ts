import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NotifierModule, NotifierOptions } from 'angular-notifier';

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

const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: 'left',
      distance: 12
    },
    vertical: {
      position: 'top',
      distance: 65,
      gap: 10
    }
  },
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};

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
    NgXCreditCardsModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
