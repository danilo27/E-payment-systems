import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClient, HttpClientModule  } from '@angular/common/http';

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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    IssueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [MagazineService,
  IssueService,
  ArticleService,
  SubscriptionService,
  TransactionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
