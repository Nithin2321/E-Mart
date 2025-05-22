import { Component, OnInit } from '@angular/core';
import { ProductserviceService } from '../productservice.service';
import { jwtDecode } from 'jwt-decode';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-productmanagement',
  standalone: false,
  templateUrl: './productmanagement.component.html',
  styleUrl: './productmanagement.component.css'
})
export class ProductmanagementComponent implements OnInit {
    productList:any[]=[];
    status:boolean=false
    newproduct:FormGroup;
    constructor(private apiservices:ProductserviceService,private router:Router){
       this.newproduct=new FormGroup({
            name:new FormControl("",[Validators.required]),
            description:new FormControl("",[Validators.required]),
            price:new FormControl("",[Validators.required]),
            category:new FormControl("",[Validators.required]),
            imageUrl:new FormControl("",[Validators.required]),
          })   
     }
    ngOnInit(): void {
      if (typeof window !== 'undefined' && localStorage.getItem("token")) {
        const token = localStorage.getItem("token");
        if (token) {
          const decoded: any = jwtDecode(token);
          if(decoded.role == "ADMIN" || "VENDOR")
          {
            this.status=true
          }
          else this.status=false
        }
      this.apiservices.getproduct().subscribe( data => {
        this.productList=data;  
    });
}
}
onSubmit(){
  if(this.newproduct.valid){
    this.router.navigate(['/products'])
  }
   const product=this.newproduct.value
   this.apiservices.insertproduct(product).subscribe({
    next: (res)=>alert("Producted added success"),
    error:(err)=>alert("Ununsuccess")
})
}
remove(item:any){
  this.apiservices.delete(item.id).subscribe(()=> {
    this.productList=this.productList.filter(pl=>pl.id !== item.id);
  })
}
}
