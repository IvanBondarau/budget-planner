import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CategoryModel} from "../../models/category.model";
import {BudgetItemModel} from "../../models/budget-item.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-category-card',
  templateUrl: './category-card.component.html',
  styleUrls: ['./category-card.component.scss']
})
export class CategoryCardComponent implements OnInit {

  @Input() category!: CategoryModel;

  @Output() removeCard = new EventEmitter<CategoryModel>();

  addItemForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    value: new FormControl('', [Validators.required, Validators.min(0), Validators.max(1000000)]),
  })

  errorText = ''

  constructor() { }

  ngOnInit(): void {
  }


  removeItem(item: BudgetItemModel) {
    this.category.items.splice(this.category.items.indexOf(item), 1)
  }

  addItem() {
    if (!(this.addItemForm.controls['name'].dirty && this.addItemForm.controls['value'].dirty)) {
      this.errorText = 'Введите значение'
      return
    } else {
      this.errorText = ''
    }

    this.category.items.push(
      new BudgetItemModel(
        null,
        this.addItemForm.value.name,
        this.addItemForm.value.value
      )
    )
  }

  onDelete() {
    this.removeCard.emit(this.category)
  }

  getTotal() {
    let sum = 0;
    for (const item of this.category.items) {
      sum += item.value
    }
    return sum;
  }

}
