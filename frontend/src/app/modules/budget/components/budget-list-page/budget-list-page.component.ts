import { Component, OnInit } from '@angular/core';
import {BudgetModel} from "../../models/budget.model";
import {BudgetService} from "../../services/budget.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-budget-page',
  templateUrl: './budget-list-page.component.html',
  styleUrls: ['./budget-list-page.component.scss']
})
export class BudgetListPageComponent implements OnInit {

  budgets: Array<BudgetModel> = Array.of();

  budgetForm = new FormGroup({
      name: new FormControl('')
    }
  )

  constructor(private budgetService: BudgetService) { }

  ngOnInit(): void {
    this.refreshBudgets()
  }

  private refreshBudgets() {
    this.budgetService.findActiveUserBudgets().subscribe(
      {
        next: value => { this.budgets = value }
      }
    )
  }

  onSubmit(): void {
    this.budgetService.createBudget(this.budgetForm.value.name).subscribe(
      {
        next: value => this.refreshBudgets()
      }
    )
  }

  deleteBudget(id: number) {
    this.budgetService.deleteBudget(id).subscribe(
      {
        next: value => this.refreshBudgets()
      }
    );
  }

}
