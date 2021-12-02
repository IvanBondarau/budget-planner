import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
    name: new FormControl(''),
    password: new FormControl('')
  });

  errorText: string | null = null;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    const observer = {
      next: (user: UserModel) => {
        this.router.navigate(['/budgets'])
      },
      error: (err: any) => { this.errorText = err?.message },
      complete: () => console.log('Login complete'),
    }

    this.userService.login(this.loginForm.value).subscribe(observer)
  }

}
