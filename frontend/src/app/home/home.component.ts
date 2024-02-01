import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this.searchDTO.amenities = []
  }
  /*
  private String location;
	private Integer guests;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date beginDate;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date endDate;
	private Integer minPrice;
	private Integer maxPrice;
	private Set<String> amenities = new HashSet<String>();
  */
  amenity = ""
  searchDTO = {
    location:"",
    guests:1,
    beginDate:"",
    endDate:"",
    minPrice:0,
    maxPrice:0,
    amenities:[""]
  }
  accommodationSeachResult = [{calculatedPrice:"",accommodation:{id:"",name:"", location:""}}]
  addService() {
    this.searchDTO.amenities.push(JSON.parse(JSON.stringify(this.amenity)))
    this.amenity = ""
  }
  removeService(amenity:any) {
    let remainingArr = this.searchDTO.amenities.filter(data => data != amenity);
    this.searchDTO.amenities = JSON.parse(JSON.stringify(remainingArr));
  }
  log(){
    //console.log(this.searchDTO)
    this._userService.searchAccommodation(this.searchDTO).subscribe(data => this.accommodationSeachResult = data,
      error => console.log(error));
  }
}
