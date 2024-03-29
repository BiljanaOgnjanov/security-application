import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-accommodation-details',
  templateUrl: './accommodation-details.component.html',
  styleUrls: ['./accommodation-details.component.css']
})
export class AccommodationDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute, private _userService: UserService) { }
  private routeSub: Subscription = Subscription.EMPTY;
  private id:string = "";
  ngOnInit(): void {
    this._userService.getMyAcoommodations().subscribe(data => null,
      error => console.log(error));
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
