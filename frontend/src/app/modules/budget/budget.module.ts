import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BudgetListPageComponent } from './components/budget-list-page/budget-list-page.component';
import { BudgetCardComponent } from './components/budget-card/budget-card.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    BudgetListPageComponent,
    BudgetCardComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class BudgetModule { }
