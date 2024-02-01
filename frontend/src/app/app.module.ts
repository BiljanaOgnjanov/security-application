import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {AuthGuard, AuthModule, AuthService} from '@auth0/auth0-angular'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AddAccommodationComponent } from './add-accommodation/add-accommodation.component';
import { ChangeAccountInfoComponent } from './change-account-info/change-account-info.component';
import { AccommodationDetailsComponent } from './accommodation-details/accommodation-details.component';
import { MyAccommodationsComponent } from './my-accommodations/my-accommodations.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { AuthInterceptorInterceptor } from './auth-interceptor.interceptor';
import { HomeComponent } from './home/home.component';
import { AccommodationDetailsGuestComponent } from './accommodation-details-guest/accommodation-details-guest.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    LoginComponent,
    AddAccommodationComponent,
    ChangeAccountInfoComponent,
    AccommodationDetailsComponent,
    MyAccommodationsComponent,
    HomeComponent,
    AccommodationDetailsGuestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthGuard,
    {provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
