import { Component, OnInit} from '@angular/core';
import { accesscontrolGuard } from '../accesscontrol.guard';
import {jwtDecode} from 'jwt-decode';
import { Router } from '@angular/router';
import { RegisterserviceService } from '../registerservice.service';
@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',

})
export class HeaderComponent implements OnInit {
  title = 'E-Mart';
  id="";
  name="";
  role="";
  status:boolean=false;
  constructor(private router:Router,private regservice:RegisterserviceService){
  }
     ngOnInit(): void {
      if (typeof window !== 'undefined' && localStorage.getItem("token")) {
        const token = localStorage.getItem("token");
        if (token) {
          const decoded: any = jwtDecode(token);
          this.id=decoded.id;
          this.name = decoded.name;
          this.role=decoded.role
          this.status=true;
        }
      }
    }
    Logout(){
      localStorage.removeItem("token");    
      this.status=false;
      this.id="";
      this.name="";
      this.role="";
      this.router.navigate(['/']);alert("Logout Successful");
    }
    getusers(){
      this.regservice.getusers().subscribe({
        next:(res)=> alert("Fetched details"),
        error:(err)=> alert("Unable to fetch details")
      })
    }
  }
