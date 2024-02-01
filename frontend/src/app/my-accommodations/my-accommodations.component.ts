import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-my-accommodations',
  templateUrl: './my-accommodations.component.html',
  styleUrls: ['./my-accommodations.component.css']
})
export class MyAccommodationsComponent implements OnInit {

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this._userService.getMyAcoommodations().subscribe(data => this.accommodations = data,
    error => console.log(error));
  }

  accommodations = [{id:"",name:"", location:""}]

  log(data :any){
  }
  deleteHome(id:any){
  }
}
