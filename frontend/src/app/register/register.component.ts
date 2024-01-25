import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor() { }

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
      }
      if(this.role === "GUEST"){
      }         
    }
    else{
      alert("Passwords don't match")
    }
    

  }
}
