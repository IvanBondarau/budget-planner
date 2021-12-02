import { Injectable } from '@angular/core';
import {UserService} from "../../../core/services/user.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {BudgetModel} from "../models/budget.model";

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  constructor(
    private httpClient: HttpClient,
    private userService: UserService) { }

  findActiveUserBudgets() {
    if (this.userService.activeUser) {
      return this.httpClient.post<Array<BudgetModel>>(
        environment.apiHost + "/budget/search",
        {
          userId: this.userService.activeUser.id
        }
      )
    } else {
      throw "User not authenticated";
    }
  }

  createBudget(name: String) {
    if (this.userService.activeUser) {
      return this.httpClient.post<Array<BudgetModel>>(
        environment.apiHost + "/budget",
        {
          userId: this.userService.activeUser.id,
          name: name
        }
      )
    } else {
      throw 1;
    }
  }

  deleteBudget(id: number) {
    return this.httpClient.delete(environment.apiHost + "/budget/" + id);

  }
}
