import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private jwtHelper: JwtHelperService;
  constructor(private router: Router) {this.jwtHelper = new JwtHelperService(); }
  userRole : any
  ngOnInit(): void {
    var token =  JSON.stringify(localStorage.getItem('token'));
    this.userRole = this.getClaim(token, 'UI_ROLE');
  }
  public decodeToken(token: string): any {
    return this.jwtHelper.decodeToken(token);
  }
  public getClaim(token: string, claimKey: string): any {
    const decodedToken = this.decodeToken(token);
    return decodedToken ? decodedToken[claimKey] : null;
  }
  logout(){

  
  if (this.userRole === "GUEST"){

  }
  if (this.userRole === "HOST"){

  }
  localStorage.removeItem('token');
  this.userRole = null
  this.router.navigate(['/home']);
  }
}
