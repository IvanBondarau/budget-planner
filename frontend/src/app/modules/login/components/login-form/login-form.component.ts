import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {UserService} from "../../../../core/services/user.service";

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

  errorText: string | null = 'ЛИЗА ХУЙ';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  async onSubmit() {
    await this.userService.login(this.loginForm.value)
    if (this.userService.activeUser != null) {
      this.errorText = 'OK'
    } else  {
      this.errorText = 'Error'
    }
  }

}
