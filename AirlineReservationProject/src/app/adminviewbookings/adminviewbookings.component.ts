import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from '../booking';
import { BookingServiceService } from '../booking-service.service';

@Component({
  selector: 'app-adminviewbookings',
  templateUrl: './adminviewbookings.component.html',
  styleUrls: ['./adminviewbookings.component.css']
})
export class AdminviewbookingsComponent implements OnInit {

  bookings:Booking[];
  bookingscan:Booking[];

  constructor(private bookService:BookingServiceService,private router:Router) { }

  ngOnInit(): void {
    this.bookService.viewAllBookingAdminTrue().subscribe(
      viewb=>{
        this.bookings=viewb;
      }
    );

    this.bookService.viewAllBookingAdminFalse().subscribe(
      viewbc=>{
        this.bookingscan=viewbc;
      }
    );
  }

  viewticket(bookingId:number){
    this.bookService.bookingIdForViewTicketData=bookingId;
    this.router.navigate(['/viewAdminTicket'])
  }

}
