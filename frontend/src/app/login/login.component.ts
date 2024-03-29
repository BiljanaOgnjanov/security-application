import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private _userService: UserService,private router: Router) { }

  ngOnInit(): void {
  }
  UserLoginDTO = {
    username:"",
    password:"",
  }
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
  log() {
    this._userService.login(this.UserLoginDTO).subscribe(data => this.setLocalStorage(data),
    error => console.log(error));
  }
}
