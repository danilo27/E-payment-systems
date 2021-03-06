import { AuthenticationService } from './services/authentication/authentication.service';
import { AuthGuard } from './auth.guard';
import { TokenInterceptor } from './token.interceptor';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from './/app-routing.module';
import { HomeComponent } from './home/home.component';



import { MagazineService } from './services/magazine/magazine.service';
import { IssueService } from './services/issue/issue.service';
import { ArticleService } from './services/article/article.service';
import { SubscriptionService } from './services/subscription/subscription.service';
import { TransactionService } from './services/transaction.service';
import { IssueComponent } from './issue/issue.component';
import { AuthorComponent } from './author/author.component';
import { LoginComponent } from './login/login.component';
 
import { ItemsComponent } from './items/items.component';
 
import { AdminComponent } from './admin/admin.component';
import { NewMerchantComponent } from './admin/new-merchant/new-merchant.component';

import { GetValuesPipe } from './pip.pipe';

import { RoleGuardGuard } from './role-guard.guard';
import { SuccessComponent } from './success/success.component';
import { FailedComponent } from './failed/failed.component';
import { ErrorComponent } from './error/error.component';

 

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    IssueComponent,
    AuthorComponent,
    LoginComponent,
    ItemsComponent,
    AdminComponent,
    NewMerchantComponent,
    GetValuesPipe,
    SuccessComponent,
    FailedComponent,
    ErrorComponent
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule      
  ],
  providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
  AuthGuard,
  RoleGuardGuard,
  MagazineService,
  IssueService,
  ArticleService,
  SubscriptionService,
  TransactionService,
 
  GetValuesPipe,
 
  AuthenticationService
 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
