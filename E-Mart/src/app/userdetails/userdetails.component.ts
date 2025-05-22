import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterserviceService } from '../registerservice.service';
interface User {
  id:number;
  name: string;
  email: string;
  role: string;
}
@Component({
  selector: 'app-userdetails',
  standalone: false,
  templateUrl: './userdetails.component.html',
  styleUrl: './userdetails.component.css'
})
export class UserdetailsComponent implements OnInit {
  users:User[]=[];
  constructor(private router:Router,private regservice:RegisterserviceService){
  }
  ngOnInit(){
   this.regservice.getusers().subscribe(
    data=> {this.users=data;
  });
  }
  remove(user:any){
    if(true)
    this.regservice.delete(user.id).subscribe(()=> {
      this.users=this.users.filter(u=>u.id !== user.id);
    })
  }
}
