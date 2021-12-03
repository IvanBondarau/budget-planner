import {Component, Input, OnInit} from '@angular/core';
import {BudgetModel} from "../../models/budget.model";
import {BudgetInfoModel} from "../../models/budget-info.model";
import {ActivatedRoute} from "@angular/router";
import {BudgetService} from "../../services/budget.service";
import {FormControl, FormGroup} from "@angular/forms";
import {CategoryModel} from "../../models/category.model";

@Component({
  selector: 'app-budget-page',
  templateUrl: './budget-page.component.html',
  styleUrls: ['./budget-page.component.scss']
})
export class BudgetPageComponent implements OnInit {

  budgetId!: number;
  budget!: BudgetInfoModel;

  addCategoryForm = new FormGroup(
    {
      name: new FormControl()
    }
  )

  constructor(private route: ActivatedRoute, private budgetService: BudgetService) {
    route.queryParams.subscribe(
      {
        next: value => {
          this.budgetId = value['id']
        }
      }
    )
  }

  ngOnInit(): void {
    this.budgetService.getBudgetInfo(this.budgetId).subscribe(
      {
        next: value => {
          this.budget = value
        }
      }
    )
  }

  addCategory(typeId: number) {

    let nextId = this.budget.incomes.length > 0 ? this.budget.incomes[this.budget.incomes.length - 1].id * 100 + 1 : 100

    if (typeId == 0) {
      this.budget.incomes.push(
        new CategoryModel(
          nextId,
          this.addCategoryForm.value.name,
          "#000000",
          0,
          []
        )
      )
    } else {
      this.budget.expenses.push(
        new CategoryModel(
          nextId,
          this.addCategoryForm.value.name,
          "#000000",
          1,
          []
        )
      )
    }
  }

  removeCategory(category: CategoryModel) {
    if (category.typeId == 0) {
      this.budget.incomes.splice(this.budget.incomes.indexOf(category), 1)
    } else {
      this.budget.expenses.splice(this.budget.expenses.indexOf(category), 1)
    }
  }

  saveBudget() {
    this.budgetService.save(this.budget).subscribe(
      {
        next: value => console.log("dadajndaad")
      }
    );
  }

}
