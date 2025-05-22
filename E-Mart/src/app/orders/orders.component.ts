import { Component, OnInit } from '@angular/core';
import { ProductserviceService } from '../productservice.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { BlobOptions } from 'buffer';
@Component({
  selector: 'app-orders',
  standalone: false,
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  constructor(private productserv:ProductserviceService,private router:Router){}
  orders: any[] = [];
  userid:string="";
   ngOnInit() {
    const token = localStorage.getItem('token');
          if (!token) {
             this.router.navigate(['/login']);
           }
           else{
             const decoded: any = jwtDecode(token);
             this.userid= decoded.id;
             } 
      this.productserv.getOrders(this.userid).subscribe({
          next: (data) => {
            console.log(data);
            this.orders = data;
            },
          error: (err) => {
            console.error('Failed to load orders', err);
    }
  });
   }
}
