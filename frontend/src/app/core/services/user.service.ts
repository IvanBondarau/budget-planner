import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { LoginData } from '../models/login-data.model';
import { User } from '../models/user.model';
import {Observable, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  _activeUser: User | null = null;

  constructor(private http: HttpClient) {
  }

  async login(data: LoginData)  {
    this.http.post<User>(environment.apiHost + "/user/login", data)
      .subscribe((value => this._activeUser = new User(value.id, value.username, value.email)));
  }

  get activeUser(): User | null {
    return this._activeUser;
  }

}
