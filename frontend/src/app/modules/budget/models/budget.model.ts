export class BudgetModel {

  constructor(
    public id: number = 0,
    public name: string = '',
    public creationDate: Date = new Date()
  ) {
  }
}
