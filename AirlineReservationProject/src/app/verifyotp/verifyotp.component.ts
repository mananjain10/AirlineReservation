import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Otp } from '../otp';

@Component({
  selector: 'app-verifyotp',
  templateUrl: './verifyotp.component.html',
  styleUrls: ['./verifyotp.component.css']
})
export class VerifyotpComponent implements OnInit {

  constructor(private router:Router) { }

  sentOtp = new Otp();
  otp1:number;
  //enteredOtp:number;
  ngOnInit(): void {
  }

  verifyOTP(enteredOtp:number){
    this.otp1 =Number (localStorage.getItem("otpSent"));
    console.log(this.otp1);
    console.log(enteredOtp);
    if(enteredOtp == this.otp1){
      this.router.navigate(['/changePasswordPage']);
    }
    else{
      alert("wrong otp");
    }
    
  }
}
