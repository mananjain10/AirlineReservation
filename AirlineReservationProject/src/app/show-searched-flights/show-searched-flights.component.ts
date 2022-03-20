import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from '../booking';
import { BookingServiceService } from '../booking-service.service';
import { Flight } from '../flight';
import { FlightServiceService } from '../flight-service.service';
import { SearchFlight } from '../search-flight';
import { Seatmap } from '../seatmap';

@Component({
  selector: 'app-show-searched-flights',
  templateUrl: './show-searched-flights.component.html',
  styleUrls: ['./show-searched-flights.component.css']
})
export class ShowSearchedFlightsComponent implements OnInit {

  flights:Flight[];
  searchF= new SearchFlight();

  booking:Booking=new Booking();
  todayDate=new Date();
  seatmap=new Seatmap();
  loggeduser:string;

   fromCity:string;
   toCity:string;
   dateT:string;

  constructor(private service:FlightServiceService,private bookService:BookingServiceService,private router:Router) { }

  ngOnInit(): void {
    this.loggeduser=localStorage.getItem("userEmail");

    
    this.fromCity=this.service.fromCityData;
    this.toCity=this.service.toCityData;
    this.dateT=this.service.dateTdata;

    this.service.searchFlight(this.fromCity.toLowerCase(),this.toCity.toLowerCase()).subscribe(
      sflight=>{
        this.flights=sflight;
        console.log(this.flights.length);
        if(this.flights.length==0){
          alert("No flights found!")
        }
        console.log(sflight);
      }
    );

    // this.service.searchFlight(this.searchF).subscribe(
    //   showFlights=>{
    //     this.flights=showFlights;
    //     console.log(this.searchF.fromCity);
    //     console.log(this.searchF.toCity);
    //     console.log(this.searchF.dateT);
    //   }
    // );
  }

  bookFlight(flight_no:number){
    console.log(this.loggeduser);
    if(this.loggeduser==null){
      this.router.navigate(['/loginPage'])
    }
    else{
    this.booking.flight_no=flight_no;
    this.bookService.flightNoData=flight_no;
    this.booking.user_id=Number(localStorage.getItem("userId"));
    this.booking.noOfPassengers=this.bookService.noOfPassengersData;
    this.booking.bookDate=this.todayDate;

    this.bookService.addBooking(this.booking).subscribe(
      bookingAdded=>{
        console.log(bookingAdded);
        if(bookingAdded){
          console.log(bookingAdded.booking_id);
          this.bookService.bookingIdData=bookingAdded.booking_id;
          this.router.navigate(['/userPassengerDetailsPage']);
        }
      }
    );}
  }



}
