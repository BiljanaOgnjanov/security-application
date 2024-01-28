import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-accommodation',
  templateUrl: './add-accommodation.component.html',
  styleUrls: ['./add-accommodation.component.css']
})
export class AddAccommodationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.AccommodationDTO.amenities.length = 0
    this.AccommodationDTO.accommodationImages.length = 0
    
  }

  AmenityDTO = {
      amenity: ""
  }
  AccommodationImageDTO = {
      img:""
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

  log() {
  }
  addService() {
    this.AccommodationDTO.amenities.push(JSON.parse(JSON.stringify(this.AmenityDTO)))
    this.AmenityDTO.amenity = ""
  }
  removeService(amenity:any) {
    let remainingArr = this.AccommodationDTO.amenities.filter(data => data != amenity);
    this.AccommodationDTO.amenities = JSON.parse(JSON.stringify(remainingArr));
  }
  removePicture(img:any) {
    let remainingArr = this.AccommodationDTO.accommodationImages.filter(data => data != img);
    this.AccommodationDTO.accommodationImages = JSON.parse(JSON.stringify(remainingArr));
  }
  addPicture() {
    this.AccommodationDTO.accommodationImages.push(JSON.parse(JSON.stringify(this.AccommodationImageDTO)))
    this.AccommodationImageDTO.img = ""
  }
  onSelectNewFile(e: Event) {
    let files = (e.target as HTMLInputElement).files
    let file = files?.item(0) as File
    let reader = new FileReader();
    reader.readAsDataURL(file as Blob)
    reader.onload = () => {
      this.AccommodationImageDTO.img = (JSON.parse(JSON.stringify(reader.result as string)))
      this.addPicture()
    }
  }
}
