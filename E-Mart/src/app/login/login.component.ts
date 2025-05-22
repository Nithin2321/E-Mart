import { Component  } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RedirectCommand, Route, Router } from '@angular/router';
import { LoginserviceService } from '../loginservice.service';
@Component({
  selector: 'app-login',
  standalone: false, 
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  })
export class LoginComponent{
  loginUser:FormGroup;
  constructor(private router:Router,private loginservice:LoginserviceService){
    this.loginUser=new FormGroup({
      email :new FormControl("",[Validators.required,Validators.email]),
      password : new FormControl("",[Validators.required,Validators.minLength(8)])
    })
  }
  onSubmit(){
    if(this.loginUser.valid){
    const user=this.loginUser.value;
    this.loginservice.login(user).subscribe({
      next : (token :any)=>
      {
        localStorage.setItem("token",token.token);
        this.router.navigate(['/home']);
        alert("login success");
      },
      error : (err)=>alert("Invalid Username or password")
       
    })
  }
}
}
