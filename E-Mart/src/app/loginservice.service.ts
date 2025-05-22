import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {
  private url="http://localhost:8081/login";
  constructor(private http:HttpClient) { }
  login(data:any){
   return  this.http.post(this.url,data);
  }
}
