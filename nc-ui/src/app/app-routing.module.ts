import { AuthGuard } from './auth.guard';
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
import { RoleGuardGuard } from './role-guard.guard';
import { SuccessComponent } from './success/success.component';
import { FailedComponent } from './failed/failed.component';
import { ErrorComponent } from './error/error.component';
 
const routes: Routes = [
	{
		path:'login',
		component: LoginComponent,
		//pathMatch: 'full'
  },
  {
		path:'',
		component: HomeComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
	},
  {
    path:'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
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
    component: AdminComponent,
    canActivate: [RoleGuardGuard], 
    data: {expectedRole: 'ADMINISTRATOR'}
  },
  {
    path:'admin/new-merchant',
    component: NewMerchantComponent,
    canActivate: [RoleGuardGuard], 
    data: {expectedRole: 'ADMINISTRATOR'}
   },
   {
    path:'success',
    component: SuccessComponent
   },
   {
    path:'failed',
    component: FailedComponent
   },
   {
    path:'error',
    component: ErrorComponent
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
