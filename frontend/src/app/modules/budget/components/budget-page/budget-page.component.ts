import {Component, OnInit} from '@angular/core';
import {BudgetInfoModel} from "../../models/budget-info.model";
import {ActivatedRoute} from "@angular/router";
import {BudgetService} from "../../services/budget.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategoryModel} from "../../models/category.model";

@Component({
  selector: 'app-budget-page',
  templateUrl: './budget-page.component.html',
  styleUrls: ['./budget-page.component.scss']
})
export class BudgetPageComponent implements OnInit {

  budgetId!: number;
  budget!: BudgetInfoModel;

  errorText1 = '';
  errorText2 = '';

  addCategoryForm = new FormGroup(
    {
      name: new FormControl('', [Validators.required])
    }
  )

  expectedControl = new FormControl()
  resultControl = new FormControl()

  colorSchemeIncome = {
    domain: ['#007f5f', '#2b9348', '#55a630', '#80b918', '#AACC00', '#BFD200']
  };

  constructor(private route: ActivatedRoute, private budgetService: BudgetService) {
    route.queryParams.subscribe(
      {
        next: value => {
          this.budgetId = value['id']
        }
      }
    )
  }


  async ngOnInit() {
    this.budget = await this.budgetService.getBudgetInfo(this.budgetId)
  }


  get incomesTotal() {
    let res = []
    for (const category of this.budget.incomes) {
      let sum = 0;
      for (const item of category.items) {
        sum += item.value
      }
      res.push(
        {
          "name": category.name,
          "value": sum
        }
      )
    }
    return res;
  }

  get expensesTotal() {
    let res = []
    for (const category of this.budget.expenses) {
      let sum = 0;
      for (const item of category.items) {
        sum += item.value
      }
      res.push(
        {
          "name": category.name,
          "value": sum
        }
      )
    }
    return res;
  }

  get totalIncome() {
    if (this.incomesTotal.length == 0) {
      return 0
    }
    return this.incomesTotal.map(x => x.value).reduce(
      (x, y) => x + y
    )
  }

  get totalExpense() {
    if (this.expensesTotal.length == 0) {
      return 0
    }
    return this.expensesTotal.map(x => x.value).reduce(
      (x, y) => x + y
    )
  }

  get totalBudget() {
    return this.totalIncome - this.totalExpense
  }

  get groupDataForCharts() {
    let res = [
      {
        "name": "Доходы",
        "series": this.incomesTotal.map(value => {
            return {
              "name": value.name,
              "value": value.value,
              "extra": {
                "code": "income"
              }
            }
          }
        )
      },
      {
        "name": "Расходы",
        "series": this.expensesTotal.map(value => {
            return {
              "name": value.name,
              "value": value.value,
              "extra": {
                "code": "expense"
              }
            }
          }
        )
      }
    ]
    console.log(res)
    return res
  }

  addCategory(typeId: number) {

    if (!this.addCategoryForm.dirty) {
      if (typeId == 0) {
        this.errorText1 = 'Введите имя категории'
      } else  {
        this.errorText2 = 'Введите имя категории'
      }
      return;
    } else {
      this.errorText1 = '';
      this.errorText2 = '';
    }

    let nextId = (this.budget.incomes.length + this.budget.expenses.length) * 100 + 1

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

  updateResult() {
    let num = Number(this.expectedControl.value)
    let sum = this.totalBudget
    let res
    if (sum != 0) {
       res = Math.ceil(num / sum)
    } else {
      res = -1
    }
    let resStr;
    if (res <= 0) {
      resStr = 'Никогда'
    } else {
      if (('' + res).endsWith('1')) {
        resStr = res + ' месяц'
      } else
      if (('' + res).endsWith('2') || ('' + res).endsWith('3') || ('' + res).endsWith('4') || ('' + res).endsWith('5')) {
        resStr = res + ' месяца'
      } else {
        resStr = res + ' месяцев'
      }
    }
    this.resultControl.setValue(resStr)
  }

}
