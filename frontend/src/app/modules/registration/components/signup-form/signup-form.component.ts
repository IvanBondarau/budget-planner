import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../../core/services/user.service";
import {RegisterData} from "../../../../core/models/register-data.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss']
})
export class SignupFormComponent implements OnInit {

  signUpForm = new FormGroup({
    username: new FormControl('',[
      Validators.pattern('/^[a-zA-Z\\d_]+$/i'),
      Validators.minLength(5),
      Validators.maxLength(20),
      Validators.required
    ]),
    email: new FormControl('', [
      Validators.email,
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.pattern('/^[a-zA-Z\\d_]+$/i'),
      Validators.minLength(8),
      Validators.maxLength(50),
      Validators.required
    ]),
    repeatPassword: new FormControl('', [
      Validators.pattern('/^[a-zA-Z\\d_]+$/i'),
      Validators.minLength(8),
      Validators.maxLength(50),
      Validators.required
    ])
  });

  errorText: string | null = null;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async onSubmit() {

    console.log(this.signUpForm.dirty)

    if (!(this.signUpForm.controls["username"].dirty && this.signUpForm.controls["email"].dirty && this.signUpForm.controls["password"].dirty)) {
      this.errorText = 'Неверный формат ввода'
      return
    }


    if (this.signUpForm.controls["password"].value != this.signUpForm.controls["repeatPassword"]) {
      this.errorText = 'Пароли не совпадают'
    }

    const res = await this.userService.register(
      new RegisterData(this.signUpForm.value.username, this.signUpForm.value.email, this.signUpForm.value.password)
    )

    if (res) {
      this.router.navigate(["/budgets"])
    } else {
      this.errorText = 'Такое имя пользователя уже занято'
    }
  }

}
