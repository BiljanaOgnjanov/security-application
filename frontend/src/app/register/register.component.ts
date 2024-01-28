import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
  }
  verifyPassword= ""
  role = "GUEST"
  UserDTO = {
    username:"",
    password:"",
    email:"",
    firstName:"",
    lastName:"",
    adress:"",
  }
  
  log() {
    if(this.verifyPassword === this.UserDTO.password){
      if (this.role === "HOST"){
        this._userService.registerHost(this.UserDTO).subscribe(data => console.log(data),
        error => console.log(error));
      }
      if(this.role === "GUEST"){
        this._userService.registerGuest(this.UserDTO).subscribe(data => console.log(data),
        error => console.log(error));
      }         
    }
    else{
      alert("Passwords don't match")
    }
    

  }
}
