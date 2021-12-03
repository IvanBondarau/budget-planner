import {CategoryModel} from "./category.model";

export class BudgetInfoModel {
  constructor(
    public id: number,
    public name: string,
    public incomes: Array<CategoryModel>,
    public expenses: Array<CategoryModel>,
  ) {
  }
}
