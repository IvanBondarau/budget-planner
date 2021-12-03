import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CategoryModel} from "../../models/category.model";
import {BudgetItemModel} from "../../models/budget-item.model";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-category-card',
  templateUrl: './category-card.component.html',
  styleUrls: ['./category-card.component.scss']
})
export class CategoryCardComponent implements OnInit {

  @Input() category!: CategoryModel;

  @Output() removeCard = new EventEmitter<CategoryModel>();

  addItemForm = new FormGroup({
    name: new FormControl(),
    value: new FormControl(),
  })

  constructor() { }

  ngOnInit(): void {
  }


  removeItem(item: BudgetItemModel) {
    this.category.items.splice(this.category.items.indexOf(item), 1)
  }

  addItem() {
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

}
