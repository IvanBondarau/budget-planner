import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../../core/services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfileForm = new FormGroup({
    username: new FormControl(),
    email: new FormControl('', [Validators.required])
  })

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
   this.refreshUser()
  }

  refreshUser() {
    let user = this.userService.activeUser
    if (user != null) {
      this.userProfileForm.setValue({
        username: user.username,
        email: user.email
      })
    }
  }

  updateUser() {
    if (!this.userProfileForm.dirty) {
      return
    }
    let user = this.userService.activeUser
    if (user == null) {
      throw "User not authenticated";
    }
    user.email = this.userProfileForm.value.email;
    this.userService.updateUser(
      user
    ).subscribe(
      {
        next: value => this.router.navigate(["/profile"])
      }
    )
  }


  logout() {
    this.userService.logout()
    this.router.navigate(['/'])
  }

}
