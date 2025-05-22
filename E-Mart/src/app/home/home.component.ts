import { Component, OnInit } from '@angular/core';
import { ProductserviceService } from '../productservice.service';
@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  productList:any[]=[];
  category1:any[]=[];
  categoryName1:string=''
  category2:any[]=[];
  categoryName2:string=''
  category3:any[]=[];
  categoryName3:string=''
  category4:any[]=[];
  categoryName4:string='' 
  constructor(private products:ProductserviceService){
  }
  ngOnInit(): void {
    this.products.getproduct().subscribe((data)=>{
      this.productList=data;
      data.forEach( (product:any) => {
        if(product.category=="men's clothing"){
          this.category1.push(product)
          this.categoryName1=product.category
        }
        else if(product.category=="jewelery"){
         this.category2.push(product)
         this.categoryName2=product.category
        }
        else if(product.category=="electronics"){
          this.category3.push(product)
          this.categoryName3=product.category
        }
        else if(product.category=="women's clothing"){
          this.category4.push(product)
          this.categoryName4=product.category
        }
        else{
          console.log("no data")
        }  
      });
    })
  }
}
