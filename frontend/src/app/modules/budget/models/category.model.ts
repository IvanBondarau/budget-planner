import {BudgetItemModel} from "./budget-item.model";

export class CategoryModel {
  constructor(
    public id: number,
    public name: string,
    public color: string | null,
    public typeId: number,
    public items: Array<BudgetItemModel>
  ) {
  }
}
