import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { EnterBuyerDetailsComponent } from './enter-buyer-details/enter-buyer-details.component';
import { RegisterNewAccountComponent } from './register-new-account/register-new-account.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent} from './home/home.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
	{
		path:'',
		component: EnterBuyerDetailsComponent,
		pathMatch: 'full'
	},
  {
    path:'newAccount',
    component: RegisterNewAccountComponent
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'logout',
    component: LogoutComponent
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
