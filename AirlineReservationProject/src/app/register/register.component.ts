import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';
import * as moment from 'moment';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User=new User();
  hide=true;
  minDate = moment(new Date()).format('YYYY-MM-DD');
  maxDate ="2003-01-01";
  
  constructor(private service:UserServiceService,private router:Router) { }

  ngOnInit(): void {
  }

  checkRegister(regform){
    if(regform.valid){
      console.log(this.user);
      this.service.registerUser(this.user).subscribe(
        userAdded=>{
          console.log(userAdded);
          if(userAdded)
          {
            this.router.navigate(['/loginPage'])
          }
        }
      );
      //alert("Form Submitted... Wait for Registered Email");
    }
    else{
      alert("Please fill details again.")
    }
  }

  
}
