import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BudgetModel} from "../../models/budget.model";
@Component({
  selector: 'app-budget-card',
  templateUrl: './budget-card.component.html',
  styleUrls: ['./budget-card.component.scss']
})
export class BudgetCardComponent implements OnInit {

  @Input() budget: BudgetModel | null = null;
  @Output() deleteButtonEvent: EventEmitter<number> = new EventEmitter<number>()

  date = '';

  options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

  constructor() { }

  ngOnInit(): void {
    if (this.budget != null) {
      this.date = new Date(this.budget.creationDate).toLocaleDateString();
    }
  }

  deletePressed() {
    this.deleteButtonEvent.emit(this.budget?.id)
  }

}
