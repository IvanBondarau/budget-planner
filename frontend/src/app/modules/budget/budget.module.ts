import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BudgetListPageComponent } from './components/budget-list-page/budget-list-page.component';
import { BudgetCardComponent } from './components/budget-card/budget-card.component';
import {ReactiveFormsModule} from "@angular/forms";
import { BudgetPageComponent } from './components/budget-page/budget-page.component';
import { CategoryCardComponent } from './components/category-card/category-card.component';
import {NgxChartsModule} from "@swimlane/ngx-charts";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    BudgetListPageComponent,
    BudgetCardComponent,
    BudgetPageComponent,
    CategoryCardComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgxChartsModule,
    RouterModule
  ]
})
export class BudgetModule { }
