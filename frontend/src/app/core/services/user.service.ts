import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { LoginData } from '../models/login-data.model';
import { User } from '../models/user.model';
import {Observable, Subscription} from "rxjs";
import {RegisterData} from "../models/register-data.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  _activeUser: User | null = null;

  constructor(private http: HttpClient) {
  }

  login(data: LoginData): Observable<User>  {
    let req = this.http.post<User>(environment.apiHost + "/user/login", data)
    req.subscribe((value => this._activeUser = new User(value.id, value.username, value.email)));
    return req;
  }

  register(data: RegisterData): Observable<User> {
    let req = this.http.post<User>(environment.apiHost + "/user/register", data)
    req.subscribe((value => this._activeUser = new User(value.id, value.username, value.email)));
    return req;
  }

  get activeUser(): User | null {
    return this._activeUser;
  }

}
