import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductserviceService } from '../productservice.service';
import { jwtDecode } from 'jwt-decode';
import { error } from 'console';
@Component({
  selector: 'app-cart',
  standalone: false,
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {
  cartList:any[]=[]
  userid:string=""
  constructor(private ps:ProductserviceService){}
   ngOnInit(): void {
         const token = localStorage.getItem('token');
         if (!token) throw new Error('No token found');
         const decoded: any = jwtDecode(token);
         this.userid= decoded.id; // change if your payload uses a different name
        if(this.userid){
          this.ps.getlist(this.userid).subscribe({
            next: (data :any) => {
              console.log(data)
               this.cartList = data;
             },
             error: (err) => {
               console.error("Failed to load cart", err);
             }
           });
         }
       }
       remove(productid: string): void {
        this.ps.remove(this.userid, productid).subscribe({
          next: (res) => {
            console.log('Backend response:', res); 
            this.cartList = this.cartList.filter(item => item.product.id !== productid);
            alert("Item is removed from the cart.");
          },
          error: (err) => {
            alert("Error removing item from the cart. Please try again later.");
            console.error("Error occurred while removing item from cart:", err);
          }
        });
      }
placeOrder() {
  console.log("Placing order...");
  this.ps.placeOrder(this.userid).subscribe({
    next: () => {
      alert('Order placed successfully!');
      this.cartList=[];
    },
    error: err => {
      console.error('Order failed:', err);
    }
  });
}
}



