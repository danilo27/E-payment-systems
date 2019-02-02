import { NewMerchantComponent } from './admin/new-merchant/new-merchant.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { IssueComponent } from './issue/issue.component';
import { AuthorComponent } from './author/author.component';
import { ItemsComponent } from './items/items.component';
import { Routes, RouterModule } from '@angular/router';
 
const routes: Routes = [
	{
		path:'login',
		component: LoginComponent,
		//pathMatch: 'full'
  },
  {
		path:'',
		component: HomeComponent,
		pathMatch: 'full'
	},
  {
    path:'home',
    component: HomeComponent 

  },
  {
    path:'issue/:id',
    component: IssueComponent
  },
  {
    path:'authors',
    component: AuthorComponent
  },
  {
 
    path:'items',
    component: ItemsComponent
  },
  {
      path:'admin',
    component: AdminComponent
  },
  {
    path:'admin/new-merchant',
    component: NewMerchantComponent
   }
  
]

@NgModule({
  imports: [
    CommonModule,
   RouterModule.forRoot(routes, { useHash: true })
  ],
  exports: [RouterModule],
  declarations: []
})

export class AppRoutingModule { }
