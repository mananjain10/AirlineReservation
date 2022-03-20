import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Loginuser } from '../loginuser';
import { Otp } from '../otp';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  constructor(private service:UserServiceService, private router:Router) { }

  otp=new Otp();
  otpSent:number;
  enteredEmail:string;
  ngOnInit(): void {
  }

  sendOTP(){
    console.log(this.otp.email);
    this.service.validEmail(this.otp.email).subscribe(
      isValidEmail=>{
        console.log(isValidEmail);
        if(isValidEmail){
          this.enteredEmail = this.otp.email;
          localStorage.setItem("enteredEmail",this.enteredEmail);
          this.service.sendOtp(this.otp.email).subscribe(
            data=>{
              if(data != null)
                {
                  this.otpSent = data;
                  console.log(this.otpSent);
                  localStorage.setItem("otpSent",this.otpSent.toString());
                  this.router.navigate(['/verifyOtpPage']);
                }else{
                        alert("Enter Valid Credentials");
                        this.router.navigate(['/loginPage']);
                      }
                    }
                    );
          
        }
        else{
          alert("Entered E-mail Id is not registered");
        }
      }
    );
    
 }

}
