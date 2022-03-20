import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Loginuser } from '../loginuser';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginuser=new Loginuser();
  user:User=new User();
  
  constructor(private service:UserServiceService,private router:Router) { }

  ngOnInit(): void {
  }

   checkLogin(loginForm:NgForm){
     if(loginForm.valid){
       //console.log(this.user);
       if(this.loginuser.email=='admin@gmail.com' && this.loginuser.password=='Admin@123'){
        this.router.navigate(['/adminDashboardPage']);
       }
       else{
       this.service.validUser(this.loginuser).subscribe(
         isValid=>{
           if(isValid)
           {
             localStorage.setItem("userEmail",this.loginuser.email);
             this.router.navigate(['/userDashboardPage']);
           }
           else{                  
                   alert("Enter Valid Credentials");
                   this.router.navigate(['/loginPage']);
                 
               }
           }
       );}
       //alert("Login Successful");
     }
     else{
       alert("Login Unsuccessful...Please fill details again.")
     }
   }



}
