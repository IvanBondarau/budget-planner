import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './modules/login/components/login-page/login-page.component';
import { LoginModule } from './modules/login/login.module';

const routes: Routes = [
  { path: "login", component: LoginPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), LoginModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
