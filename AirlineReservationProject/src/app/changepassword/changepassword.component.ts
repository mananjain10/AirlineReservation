import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Newpassword } from '../newpassword';
import { Otp } from '../otp';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {

  constructor(private service:UserServiceService, private router:Router) { }
  newpwd = new Newpassword();
  //currentPassword:string;
  currentEmail:string = localStorage.getItem("enteredEmail");
  ngOnInit(): void {
  }
  updatePassword(password:string){
    this.service.resetPassword(this.currentEmail,password).subscribe(
      data=>{
        console.log(this.currentEmail);
        console.log(password);
       
        //this.currentPassword = password;
        alert("Password Reset Successful");
        this.router.navigate(['/loginPage']);

        
      }       
    );
  }
}
