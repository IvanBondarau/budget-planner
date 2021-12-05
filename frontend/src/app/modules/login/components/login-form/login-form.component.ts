import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from "../../../../core/services/user.service";
import {Observable, observable, Subscription} from "rxjs";
import {UserModel} from "../../../../core/models/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  loginForm = new FormGroup({
    name: new FormControl('', [
      Validators.pattern('/^[a-zA-Z\\d_]+$/i'),
      Validators.minLength(5),
      Validators.maxLength(20),
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.pattern('/^[a-zA-Z\\d_]+$/i'),
      Validators.minLength(8),
      Validators.maxLength(50),
      Validators.required
      ]
    )
  });

  errorText: string | null = null;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async onSubmit() {

    console.log(this.loginForm.dirty)
    if (!this.loginForm.controls["name"].dirty) {
      this.errorText = 'Имя пользователя должно быть не менее 5 и не более 20 символов и состоять из букв латинского алфавита, цифр и знаков _'
      return
    }

    if (!this.loginForm.controls["password"].dirty) {
      this.errorText = 'Пароль должно быть не менее 8 и не более 50 символов и состоять из букв латинского алфавита, цифр и знаков _\n'
      return
    }

    const sub = await this.userService.login(this.loginForm.value)
    if (this.userService.activeUser) {
      this.router.navigate(['/budgets'])
    } else {
      this.errorText = "Неправильное имя пользователя или пароль"
    }
  }

}
