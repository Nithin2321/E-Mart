import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ProductserviceService } from '../productservice.service';
import { NavigationStart, Router } from '@angular/router';
import { Location } from '@angular/common';
import { error } from 'node:console';
import { jwtDecode } from 'jwt-decode';
@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  productList:any[]=[];
  errorMessage:String="";
  id:string="";
  constructor(private apiservices:ProductserviceService,private router:Router){ }
  addtocart(productId: string) {
    const quantity=1;
    if(!this.id){
      this.router.navigate(['/login'])
    }
    this.apiservices.addToCart(this.id, productId, quantity).subscribe({
      next: (res) => {
        alert('Product added to cart');
      },
      error: (err) => {
        console.error('Failed to add to cart', err);
      }
    });
  }
  ngOnInit(): void {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        this.id = decoded.id;
      }
      catch (err) {
         this.errorMessage = "Invalid token format"
      }
    }
    this.apiservices.getproduct().subscribe( data => {
      this.productList=data;  
  });
}
}

