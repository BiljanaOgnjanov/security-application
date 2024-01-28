import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accommodation-details',
  templateUrl: './accommodation-details.component.html',
  styleUrls: ['./accommodation-details.component.css']
})
export class AccommodationDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  AccommodationDTO = {
    name:"",
    location:"",
    minGuests:0,
    maxGuests:0,
    priceCalculation:"",
    amenities:[{amenity:""}],
    accommodationImages:[{img:""}],
  }
  show = "details"
  dateFrom =""
  dateTo = ""
  
  log() {
  }
  details() {
    this.show = "details"
  }
  calendar() {
    this.show = "calendar"
  }
  
  
  reservations = [{id:"",endDate:"",startingDate:"",status:"",reviewStatus:"",reviewText:"",customer:{id:"",name:"",email:"",surname:""}}]
  
  currentReservation(){
  }
  
  currentRes = {id:"",endDate:"",startingDate:"",status:"",reviewStatus:"",reviewText:"",customer:{id:"",name:"",email:"",surname:""}}
  freeTimeSlots=[{endDate:"",startingDate:"",status:""}]
  freeFrom = ""
  freeTo = ""
  reserveFrom = ""
  reserveTo = ""
  getFreeTimeSlots() {
  }
  reserve() {
  }
  
  addFreeDates() {
  }
  timeSlots = [{endDate:"",startingDate:"",status:""}]
  getTimeSlots() {
  }
  
}
