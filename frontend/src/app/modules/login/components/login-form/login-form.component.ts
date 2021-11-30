import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {UserService} from "../../../../core/services/user.service";
import {Observable, observable, Subscription} from "rxjs";
import {User} from "../../../../core/models/user.model";

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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    const observer = {
      next: (user: User) => {},
      error: (err: any) => { this.errorText = 'Text' },
      complete: () => console.log('Login complete'),
    }

    this.userService.login(this.loginForm.value).subscribe(observer)
  }

}
