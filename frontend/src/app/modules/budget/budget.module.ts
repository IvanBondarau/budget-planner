import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BudgetListPageComponent } from './components/budget-list-page/budget-list-page.component';
import { BudgetCardComponent } from './components/budget-card/budget-card.component';
import {ReactiveFormsModule} from "@angular/forms";
import { BudgetPageComponent } from './components/budget-page/budget-page.component';
import { CategoryCardComponent } from './components/category-card/category-card.component';



@NgModule({
  declarations: [
    BudgetListPageComponent,
    BudgetCardComponent,
    BudgetPageComponent,
    CategoryCardComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class BudgetModule { }
