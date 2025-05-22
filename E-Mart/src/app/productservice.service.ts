import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface products {
  id: string;
  name: string;
  description: string;
  price: string;
  category: string;   
  imageUrl:String; 
}
interface User{
  id:String;
  role:String;
}
@Injectable({
  providedIn: 'root'
})
export class ProductserviceService {
  private api='http://localhost:8081/getproducts';
  constructor(private http:HttpClient) { }
  getproduct():Observable<any>{
    return this.http.get(this.api);
  }
  getproductbyid(id:String):Observable<products> {
    return this.http.get<products>(`http://localhost:8081/getproduct/${id}`);
  }
  insertproduct(products:any){
    return this.http.post("http://localhost:8081/newproduct",products);
  }
  delete(id:string):Observable<any>{
    return this.http.delete(`http://localhost:8081/removeproduct/${id}`);
  }
  addToCart(userId: string, productId: string, quantity: number): Observable<any> {
    const params = new HttpParams()
      .set('userId', userId)
      .set('productId', productId)
      .set('quantity', quantity.toString());
    return this.http.post('http://localhost:8081/addToCart', null, { params });
  }
  getlist(UserId:string){
    return this.http.get(`http://localhost:8081/Getcart/${UserId}`);
  }
  remove(userid:string,productid:string):Observable<any>{
    const params = new HttpParams()
      .set('userId', userid)
      .set('productId', productid)
     return this.http.delete('http://localhost:8081/remove', { params });
  }
  placeOrder(userId: string): Observable<any> {
  return this.http.post('http://localhost:8081/placeOrder', null, {
    params: new HttpParams().set('userId', userId)
  });
}
  getOrders(userId: string): Observable<any> {
  return this.http.get(`http://localhost:8081/getorders/${userId}`);
}
}
