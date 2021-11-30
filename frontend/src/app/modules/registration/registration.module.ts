import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SignupFormComponent} from "../components/signup-form/signup-form.component";
import {SignupPageComponent} from "../components/signup-page/signup-page.component";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    SignupFormComponent,
    SignupPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    SignupPageComponent
  ]
})
export class RegistrationModule { }
