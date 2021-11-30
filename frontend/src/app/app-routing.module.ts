import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './modules/login/components/login-page/login-page.component';
import { LoginModule } from './modules/login/login.module';
import {RegistrationModule} from "./modules/registration/registration.module";
import {SignupPageComponent} from "./modules/components/signup-page/signup-page.component";

const routes: Routes = [
  { path: "login", component: LoginPageComponent },
  { path: "register", component: SignupPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), LoginModule, RegistrationModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
