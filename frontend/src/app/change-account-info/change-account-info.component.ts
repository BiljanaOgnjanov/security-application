import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-change-account-info',
  templateUrl: './change-account-info.component.html',
  styleUrls: ['./change-account-info.component.css']
})
export class ChangeAccountInfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  ChangeAccountInfoRequest = {
    username:"",
    email:"",
    firstName:"",
    lastName:"",
    adress:"",
  }
  
  log() {
  }
}
