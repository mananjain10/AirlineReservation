import { Component, OnInit } from '@angular/core';
import { BookingServiceService } from '../booking-service.service';
import { Flight } from '../flight';
import { FlightServiceService } from '../flight-service.service';
import { Passenger } from '../passenger';
import { Ticket } from '../ticket';

@Component({
  selector: 'app-adminviewticket',
  templateUrl: './adminviewticket.component.html',
  styleUrls: ['./adminviewticket.component.css']
})
export class AdminviewticketComponent implements OnInit {

  
  flight=new Flight();
  tickets:Ticket[];
  passengers:Passenger[];
  ticket=new Ticket();
  
  constructor(private flightService:FlightServiceService,public bookService:BookingServiceService) { }
  

  ngOnInit(): void {
    this.bookService.findFlightByBookingId(this.bookService.bookingIdForViewTicketData).subscribe(
      findFlight=>{
        this.flight=findFlight;
      }
    );

    this.bookService.findTicketByBookingId(this.bookService.bookingIdForViewTicketData).subscribe(
      findTickets=>{
        this.tickets=findTickets;
        
      }
    );

    this.bookService.findPassByBookingId(this.bookService.bookingIdForViewTicketData).subscribe(
      findpasseng=>{
        this.passengers=findpasseng;
      }
    );
  }


}
