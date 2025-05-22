import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from '../productservice.service';
import { jwtDecode } from 'jwt-decode';
@Component({
  selector: 'app-productdetails',
  standalone: false,
  templateUrl: './productdetails.component.html',
  styleUrl: './productdetails.component.css'
})
export class ProductdetailsComponent implements OnInit{
  id:string="";
  name: string="";
  description: string="";
  price: string="";
  category: string=""; 
  imageUrl:String=""; 
  releatedproduct:any[]=[];
  errorMessage:String="";
  userId:string="";
  constructor(private route:ActivatedRoute ,private ps:ProductserviceService,private router:Router){}
  addtocart(productId: string) {
    const quantity=1;
    if(!this.userId){
      this.router.navigate(['/login'])
    }
    this.ps.addToCart(this.userId, productId, quantity).subscribe({
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
            this.userId = decoded.id;
          }
          catch (err) {
             this.errorMessage = "Invalid token format"
          }
        } 
    this.route.params.subscribe(params => {
      const pid = params['id'];  // Get the dynamic ID from route
      // Scroll to top on route change
      window.scrollTo(0, 0);
      this.ps.getproductbyid(pid).subscribe((data) => {
        console.log(data)
         this.id=data.id;
         this.name=data.name;
         this.description=data.description;
         this.price=data.price;
         this.category=data.category;
         this.imageUrl=data.imageUrl;
        if (data.category) {
          // Clear the array before adding new items
          //t = data.filter((item: any) => item.category === this.productdetail.category && item.id !== this.productdetail.id);
         this.ps.getproduct().subscribe( data=>{
          this.releatedproduct=data.filter((item:any)=> item.category === this.category && item.id !== this.id)
         })
         } 
       })
    })
}  
}
