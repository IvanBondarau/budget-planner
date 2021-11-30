import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { CoreModule } from 'src/app/core/core.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonsModule } from 'ngx-bootstrap/buttons'

import { BrowserModule } from '@angular/platform-browser';
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    LoginFormComponent,
    LoginPageComponent
  ],
    imports: [
        BrowserModule,
        ButtonsModule,

        CommonModule,
        CoreModule,

        FlexLayoutModule,
        ReactiveFormsModule,
        RouterModule
    ],
  exports: [
    LoginFormComponent,
    LoginPageComponent
  ]
})
export class LoginModule { }
