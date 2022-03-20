import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from "../user";
import { UserServiceService } from "../user-service.service";

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  user:User=new User();
  userId:number;
  userEmail:string;

  constructor(private service:UserServiceService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    this.userId=Number(localStorage.getItem("userId"));
    this.service.findUserById(this.userId).subscribe(
      fuser=>{
        this.user=fuser;
        console.log(this.userId);
      }
    );
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
  }

  updateUser(userForm1){
    if(userForm1.valid){
      this.userId=Number(localStorage.getItem("userId"));
    this.service.updateUser(this.user,this.userId).subscribe(
      userPersisted=>{
        console.log(userPersisted);
        alert("Updated Successfully")
        location.reload();
      }
    );
    }
    else{
      alert("Please fill details again.")
    }
  }

  updateUser1():void{
    this.userId=Number(localStorage.getItem("userId"));
    this.service.updateUser(this.user,this.userId).subscribe(
      userPersisted=>{
        console.log(userPersisted);
        alert("Updated Successfully")
        location.reload();
      }
    );
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
