import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule} from "@angular/router";
import {AuthGuard} from "./guards/auth.guard";
import { DefaultPageComponent } from './components/default-page/default-page.component';

@NgModule({
  declarations: [
    HeaderComponent,
    DefaultPageComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    RouterModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
