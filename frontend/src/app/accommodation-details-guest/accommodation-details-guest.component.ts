import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-accommodation-details-guest',
  templateUrl: './accommodation-details-guest.component.html',
  styleUrls: ['./accommodation-details-guest.component.css']
})
export class AccommodationDetailsGuestComponent implements OnInit {

  constructor(private route: ActivatedRoute, private _userService: UserService) { }
  private routeSub: Subscription = Subscription.EMPTY;
  private id:string = "";
  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this._userService.getAccommodationInfo(this.id).subscribe(data => this.AccommodationDTO = data,
      error => console.log(error));
  }
  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }


  AccommodationDTO = {
    name:"",
    location:"",
    minGuests:0,
    maxGuests:0,
    priceCalculation:"",
    amenities:[{amenity:""}],
    accommodationImages:[{img:""}],
    timeSlot:[{id:"",startDate:"",endDate:"",status:""}]
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
    console.log(this.AccommodationDTO)
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
