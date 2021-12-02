import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../../../core/services/user.service";
import {RegisterData} from "../../../../core/models/register-data.model";

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss']
})
export class SignupFormComponent implements OnInit {

  signUpForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    repeatPassword: new FormControl('')
  });

  errorText: string | null = null;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.userService.register(
      new RegisterData(this.signUpForm.value.username, this.signUpForm.value.email, this.signUpForm.value.password)
    ).subscribe(
      {
        next: value => {},
        error: err => {
          this.errorText = "Error";
        }
      }
    )
  }

}
