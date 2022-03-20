import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css']
})
export class UserdashboardComponent implements OnInit {

  
  constructor(private service:UserServiceService,private router:Router) { }

  userEmail:string;
  user:User=new User();
  userId:number;

  ngOnInit(): void {

    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
    this.service.findUserByEmail(this.userEmail).subscribe(
      fetchedUser=>{
        console.log(JSON.stringify(fetchedUser));
        this.user=fetchedUser;
        localStorage.setItem("userId",this.user.user_id.toString());
      }
    );

  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
