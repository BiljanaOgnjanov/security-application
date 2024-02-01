import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'; 
import { AddAccommodationComponent } from './add-accommodation/add-accommodation.component';
import { ChangeAccountInfoComponent } from './change-account-info/change-account-info.component';
import { AccommodationDetailsComponent } from './accommodation-details/accommodation-details.component';
import { MyAccommodationsComponent } from './my-accommodations/my-accommodations.component';
import { HomeComponent } from './home/home.component';
import { AccommodationDetailsGuestComponent } from './accommodation-details-guest/accommodation-details-guest.component';
const routes: Routes = [
  {path:'register', component: RegisterComponent},
  {path:'login', component: LoginComponent},
  {path:'add-accommodation', component: AddAccommodationComponent},
  {path:'change-info', component: ChangeAccountInfoComponent},
  {path:'accommodation-details/:id', component: AccommodationDetailsComponent},
  {path:'my-accommodations', component: MyAccommodationsComponent},
  {path:'home', component: HomeComponent},
  {path:'accommodation-details-guest/:id', component: AccommodationDetailsGuestComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
