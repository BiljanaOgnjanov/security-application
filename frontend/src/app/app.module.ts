import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {AuthModule} from '@auth0/auth0-angular'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AddAccommodationComponent } from './add-accommodation/add-accommodation.component';
import { ChangeAccountInfoComponent } from './change-account-info/change-account-info.component';
import { AccommodationDetailsComponent } from './accommodation-details/accommodation-details.component';
import { MyAccommodationsComponent } from './my-accommodations/my-accommodations.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    LoginComponent,
    AddAccommodationComponent,
    ChangeAccountInfoComponent,
    AccommodationDetailsComponent,
    MyAccommodationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AuthModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
