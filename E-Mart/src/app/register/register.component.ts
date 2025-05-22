import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterserviceService } from '../registerservice.service';
@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  UserReg:FormGroup;
  constructor(private router:Router,private regservice :RegisterserviceService){
    this.UserReg=new FormGroup({
      name:new FormControl("",[Validators.required]),
      email:new FormControl("",[Validators.required,Validators.email]),
      phone:new FormControl("",[Validators.required,Validators.pattern("^[6-9][0-9]{9}$")]),
      role:new FormControl("CUSTOMER"),
      password:new FormControl("",[Validators.required,Validators.minLength(8)]),
      confirmPassword:new FormControl("",[Validators.required]),
      pincode:new FormControl("",[Validators.required,Validators.minLength(6)]),
      address:new FormControl("",[Validators.required]),
    })   
  }
    onSubmit(){
      if(this.UserReg.valid){
        this.router.navigate(['/login'])
      }
    const user=this.UserReg.value;
    delete user.confirmPassword;
      this.regservice.register(user).subscribe({
        next: (res)=>alert("registration success"),
        error:(err)=>alert("Registration unsuccess")
      })
    }
}
