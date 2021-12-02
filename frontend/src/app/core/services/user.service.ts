import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { LoginData } from '../models/login-data.model';
import { UserModel } from '../models/user.model';
import {Observable, Subscription} from "rxjs";
import {RegisterData} from "../models/register-data.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  _activeUser: UserModel | null = null;

  constructor(private http: HttpClient) {
  }

  login(data: LoginData): Observable<UserModel>  {
    let req = this.http.post<UserModel>(environment.apiHost + "/user/login", data)
    req.subscribe((value => this._activeUser = new UserModel(value.id, value.username, value.email)));
    return req;
  }

  register(data: RegisterData): Observable<UserModel> {
    let req = this.http.post<UserModel>(environment.apiHost + "/user/register", data)
    req.subscribe((value => this._activeUser = new UserModel(value.id, value.username, value.email)));
    return req;
  }

  get activeUser(): UserModel | null {
    return this._activeUser;
  }

  updateUser(user: UserModel) {
    let req = this.http.put<UserModel>(environment.apiHost + "/user/" + user.id, user)
    req.subscribe((value => this._activeUser = new UserModel(value.id, value.username, value.email)));
    return req;
  }

  logout() {
    this._activeUser = null;
  }

}
