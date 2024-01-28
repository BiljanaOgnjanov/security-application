import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-accommodations',
  templateUrl: './my-accommodations.component.html',
  styleUrls: ['./my-accommodations.component.css']
})
export class MyAccommodationsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  accommodations = [{id:"",name:"", location:""}]

  log(data :any){
  }
  deleteHome(id:any){
  }
}
