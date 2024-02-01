import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-account-info',
  templateUrl: './change-account-info.component.html',
  styleUrls: ['./change-account-info.component.css']
})
export class ChangeAccountInfoComponent implements OnInit {

  constructor(private _userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this._userService.getAccountInfo().subscribe(data => this.ChangeAccountInfoRequest = data,
    error => console.log(error));
  }
  ChangeAccountInfoRequest = {
    username:"",
    email:"",
    firstName:"",
    lastName:"",
    adress:"",
  }
  ChangePasswordRequest = {
    oldPassword:"",
    newPassword:"",
  }
  
  checkPassword = ""
  setLocalStorage(data:any){
    if (data ==null){
      alert("Try again")
    }else{
      localStorage.setItem('token', data.token);
      this.router.navigate(['/home'])
      .then(() => {
        window.location.reload();
        });
    }
  }
  removeLocalStorage(data:any){
      localStorage.removeItem('token');
      this.router.navigate(['/home'])
      .then(() => {
        window.location.reload();
        });
  }
  log() {
    this._userService.changeAccountInfo(this.ChangeAccountInfoRequest).subscribe(data => this.setLocalStorage(data),
      error => console.log(error));
  }
  changePass() {
    if(this.checkPassword === this.ChangePasswordRequest.newPassword){    
      this._userService.changePassword(this.ChangePasswordRequest).subscribe(data => this.removeLocalStorage(data),
      error => console.log(error));
    }
    else{
      alert("Passwords don't match")
    }
  }
}
