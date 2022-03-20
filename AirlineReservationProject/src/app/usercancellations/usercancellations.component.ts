import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from '../booking';
import { BookingServiceService } from '../booking-service.service';

@Component({
  selector: 'app-usercancellations',
  templateUrl: './usercancellations.component.html',
  styleUrls: ['./usercancellations.component.css']
})
export class UsercancellationsComponent implements OnInit {

  constructor(private bookService:BookingServiceService,private router:Router) { }

  bookings:Booking[];
  userId:number;
  userEmail:string;

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
    this.userId=Number(localStorage.getItem("userId"));
    this.bookService.viewBookingByFalseStatus(this.userId).subscribe(
      viewTrue=>{
        this.bookings=viewTrue;
      }
    );
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
