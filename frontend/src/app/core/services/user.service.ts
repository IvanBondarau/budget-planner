import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {LoginData} from '../models/login-data.model';
import {UserModel} from '../models/user.model';
import {RegisterData} from "../models/register-data.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  _activeUser: UserModel | null = null;

  constructor(private http: HttpClient) {
    let user = localStorage.getItem("User")
    if (user != null) {
      this._activeUser = JSON.parse(user)
    }
  }

  async login(data: LoginData): Promise<Boolean>  {
    let promise = this.http.post<UserModel>(environment.apiHost + "/user/login", data).toPromise();
    await promise
      .then((model) => this._activeUser = model)
      .catch((e) => this._activeUser = null)
    console.log(this._activeUser)
    if (this._activeUser == null) {
      return false;
    }
    localStorage.setItem("User", JSON.stringify(this._activeUser))
    return true;
  }

  async register(data: RegisterData): Promise<Boolean> {
    let promise = this.http.post<UserModel>(environment.apiHost + "/user/register", data).toPromise()
    await promise
      .then((model) => this._activeUser = model)
      .catch((e) => this._activeUser = null)
    console.log(this._activeUser)
    if (this._activeUser == null) {
      return false;
    }
    localStorage.setItem("User", JSON.stringify(this._activeUser))
    return true;
  }

  get activeUser(): UserModel | null {
    return this._activeUser;
  }

  updateUser(user: UserModel) {
    let req = this.http.put<UserModel>(environment.apiHost + "/user/" + user.id, user)
    req.subscribe((value => {
      this._activeUser = new UserModel(value.id, value.username, value.email);
      localStorage.setItem("User", JSON.stringify(this._activeUser))
    }));

    return req;
  }

  logout() {
    this._activeUser = null;
    localStorage.removeItem("User")
  }

}
