import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { RegisterserviceService } from '../registerservice.service';
@Component({
  selector: 'app-profile',
  standalone: false,
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  id: string = '';
  name:String="";
  email: String="";
  phone:String="";
  pincode:String="";
  address:String="";
  errorMessage: string = "";
  isEdit:boolean=false
  constructor(private router:Router,private regservice:RegisterserviceService){
  }
  ngOnInit(): void {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        this.id = decoded.id;
        this.name=decoded.name
        // ✅ Use subscribe error handler instead of try/catch
        this.regservice.getuser(this.id).subscribe({
          next: (data) =>{ 
            this.email = data.email;
            this.phone=data.phone;
            this.pincode=data.pincode;
            this.address=data.address;
          },
          error: () => this.errorMessage = "User not found or unauthorized"
        });
      } catch (err) {
        this.errorMessage = "Invalid token format";
      }
    } else {
      this.errorMessage = "No token found. Please log in.";
    }
  }  
  }

