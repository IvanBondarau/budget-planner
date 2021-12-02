import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './modules/login/components/login-page/login-page.component';
import { LoginModule } from './modules/login/login.module';
import {RegistrationModule} from "./modules/registration/registration.module";
import {SignupPageComponent} from "./modules/registration/components/signup-page/signup-page.component";
import {BudgetModule} from "./modules/budget/budget.module";
import {BudgetListPageComponent} from "./modules/budget/components/budget-list-page/budget-list-page.component";
import {ProfileModule} from "./modules/profile/profile.module";
import {UserProfileComponent} from "./modules/profile/components/user-profile/user-profile.component";

const routes: Routes = [
  { path: "login", component: LoginPageComponent },
  { path: "register", component: SignupPageComponent },
  { path: 'budgets', component: BudgetListPageComponent },
  { path: 'profile', component: UserProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), LoginModule, RegistrationModule, BudgetModule, ProfileModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
