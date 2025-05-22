import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { Routes, RouterModule } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { FooterComponent } from './footer/footer.component';
import { ProductmanagementComponent } from './productmanagement/productmanagement.component';
import { accesscontrolGuard } from './accesscontrol.guard';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { ProductdetailsComponent } from './productdetails/productdetails.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserdetailsComponent } from './userdetails/userdetails.component';
import { ProfileComponent } from './profile/profile.component';
import { OrdersComponent } from './orders/orders.component'
const routes:Routes =[
  { 
    path: '', redirectTo: 'home', pathMatch: 'full' 
  },
  {
    path:"home", component:HomeComponent
  },
  {
    path:"login", component:LoginComponent
  },
  {
    path:"register", component:RegisterComponent
  },
  {
    path:"products",component:ProductsComponent
  },
  {
    path:"product_management",component:ProductmanagementComponent,canActivate: [accesscontrolGuard],data: {roles: ['ADMIN','VENDOR']}
  },
  {
    path:"cart",component:CartComponent,canActivate:[accesscontrolGuard],data:{roles :['ADMIN','VENDOR',"CUSTOMER"]}
  },
  {
    path:"productdetails/:id",component:ProductdetailsComponent
  },
  {
    path:"userdetails",component:UserdetailsComponent,canActivate:[accesscontrolGuard],data:{roles:['ADMIN']}
  },
  {
    path:"profile",component:ProfileComponent
  },
  {
    path:"order",component:OrdersComponent
  }

]
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    ProductsComponent,
    FooterComponent,
    ProductmanagementComponent,
    HomeComponent,
    CartComponent,
    ProductdetailsComponent,
    UserdetailsComponent,
    ProfileComponent,
    OrdersComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
