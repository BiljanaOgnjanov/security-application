import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'; 
import { AddAccommodationComponent } from './add-accommodation/add-accommodation.component';
import { ChangeAccountInfoComponent } from './change-account-info/change-account-info.component';
import { AccommodationDetailsComponent } from './accommodation-details/accommodation-details.component';
import { MyAccommodationsComponent } from './my-accommodations/my-accommodations.component';
const routes: Routes = [
  {path:'register', component: RegisterComponent},
  {path:'login', component: LoginComponent},
  {path:'add-accommodation', component: AddAccommodationComponent},
  {path:'change-info', component: ChangeAccountInfoComponent},
  {path:'accommodation-details/:id', component: AccommodationDetailsComponent},
  {path:'my-accommodations', component: MyAccommodationsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
