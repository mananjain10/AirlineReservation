import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-adminviewusers',
  templateUrl: './adminviewusers.component.html',
  styleUrls: ['./adminviewusers.component.css']
})
export class AdminviewusersComponent implements OnInit {

  users:User[];

  constructor(private service:UserServiceService,private router:Router) { }

  ngOnInit(): void {
    this.service.viewAllUsers().subscribe(
      userData=>{
        this.users=userData;
      }
    );
  }

}
