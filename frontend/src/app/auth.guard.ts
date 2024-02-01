import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private _router: Router){

  }

  canActivate(): boolean{
    var token =  localStorage.getItem('token');
    if(token == null){
      this._router.navigate(['/login'])
      return false;
    }else{
      return true;
    }
  }
  
  
}
