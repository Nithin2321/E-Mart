import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface User {
  id:number;
  name: string;
  email: string;
  role: string;
  phone: string;
  pincode: string; 
  address: string;  
}
@Injectable({
  providedIn: 'root'
})
export class RegisterserviceService {
  private baseurl="http://localhost:8081/RegisterUser";
  private baseurl1="http://localhost:8081/GetallUsers";
  private baseurl2="";
  constructor(private http:HttpClient) { }
  register(user:any){
    return this.http.post(this.baseurl,user)
  }
  getusers(): Observable<User[]>{
    return this.http.get<User[]>(this.baseurl1)
  }
  getuser(id:string):Observable<User>{
    return this.http.get<User>(`http://localhost:8081/Getuser/${id}`);
  }
  delete(id:string):Observable<any>{
    return this.http.delete(`http://localhost:8081/RemoveUser/${id}`);
  }
}
