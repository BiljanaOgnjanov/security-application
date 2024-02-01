import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  getAuthoHeader() : any {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("token")
    }
    return{
      headers: headers
    };
  }
  getJsonHeader() : any {
    const headers = {
      'Content-Type': 'application/json'
    }
    return{
      headers: headers
    };
  }

  registerHost(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/auth/registerHost", JSON.stringify(user), this.getJsonHeader() );
  }
  changeAccountInfo(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/user/changeAcoountInfo", JSON.stringify(user), this.getAuthoHeader() );
  }
  changePassword(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/user/changePassword", JSON.stringify(user), this.getAuthoHeader() );
  }
  registerGuest(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/auth/registerGuest", JSON.stringify(user), this.getJsonHeader() );
  }
  login(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/auth/login", JSON.stringify(user), this.getJsonHeader() );
  }
  addAccommodation(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/accommodation/addAccommodation", JSON.stringify(user), this.getAuthoHeader() );
  }
  searchAccommodation(user: any): Observable<any> {
    return this.http.post<any>("https://localhost:8080/api/accommodation/public/search", JSON.stringify(user), this.getJsonHeader() );
  }
  getAccountInfo(): Observable<any> {
    return this.http.get<any>("https://localhost:8080/api/user/acoountInfo",this.getAuthoHeader() );
  }
  getMyAcoommodations(): Observable<any> {
    return this.http.get<any>("https://localhost:8080/api/accommodation/listMyAccommodations",this.getAuthoHeader() );
  }
  getAccommodationInfo(id: string): Observable<any> {
    return this.http.get<any>("https://localhost:8080/api/accommodation/public/"+id, this.getAuthoHeader() );
  }
}
