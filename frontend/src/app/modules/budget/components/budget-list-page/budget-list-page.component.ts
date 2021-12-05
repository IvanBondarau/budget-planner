import { Component, OnInit } from '@angular/core';
import {BudgetModel} from "../../models/budget.model";
import {BudgetService} from "../../services/budget.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CategoryModel} from "../../models/category.model";

@Component({
  selector: 'app-budget-page',
  templateUrl: './budget-list-page.component.html',
  styleUrls: ['./budget-list-page.component.scss']
})
export class BudgetListPageComponent implements OnInit {

  budgets!: Array<BudgetModel>;

  errorText = '';

  budgetForm = new FormGroup({
      name: new FormControl('', [Validators.required])
    }
  )

  constructor(private budgetService: BudgetService, private router: Router) { }

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
    if (!this.budgetForm.dirty) {
      this.errorText = 'Введите значение'
      return
    } else {
      this.errorText = ''
    }
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

  selectBudget(id: number) {
    console.log("Id: " + id);
    this.router.navigate(['/budget'], { queryParams: { id: id } })
  }


}
