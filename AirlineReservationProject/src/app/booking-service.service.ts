import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from './booking';
import { Emaildto } from './emaildto';
import { Flight } from './flight';
import { Passenger } from './passenger';
import { Seatmap } from './seatmap';
import { Ticket } from './ticket';

@Injectable({
  providedIn: 'root'
})
export class BookingServiceService {

  noOfPassengersData:number;
  seatData=[];
  passengerIdData;
  flightNoData:number;
  bookingIdData:number;
  bookingIdForViewTicketData:number;

  constructor(private httpClient:HttpClient) { }

  addBooking(booking:Booking):Observable<Booking>{
    return this.httpClient.post<Booking>("http://localhost:9090/booking",booking);
  }

  addPassenger(passenger:Passenger):Observable<Passenger>{
    return this.httpClient.post<Passenger>("http://localhost:9090/addpassenger",passenger);
  }

  addTicket(ticket:Ticket):Observable<Ticket>{
    return this.httpClient.post<Ticket>("http://localhost:9090/addTickets",ticket);
  }  

  // addSeat(seatmap:Seatmap):Observable<Seatmap>{
  //   return this.httpClient.post<Ticket>("http://localhost:9090/addSeats",seatmap);
  // }
  findFlightById(flight_no:number):Observable<Flight>{
    return this.httpClient.get<Flight>("http://localhost:9090/searchforbooking/"+flight_no);
  }

  findBookinfById(booking_id:number):Observable<Booking>{
    return this.httpClient.get<Booking>("http://localhost:9090/findbybookid/"+booking_id);
  }

  findTicketByBookingId(booking_id:number):Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>("http://localhost:9090/searchticketforbooking/"+booking_id);
  }

  findPassByTicketId(ticket_id:number):Observable<Passenger>{
    return this.httpClient.get<Passenger>("http://localhost:9090/searchpassforbooking/"+ticket_id);
  }

  findPassByBookingId(booking_id:number):Observable<Passenger[]>{
    return this.httpClient.get<Passenger[]>("http://localhost:9090/searchpassforbookings/"+booking_id);
  }

  findTicketSeats(flight_no:number):Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>("http://localhost:9090/findSeats/"+flight_no);
  }

  viewBookingByTrueStatus(user_id:number):Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/bookandtick/"+user_id);
  }

  viewBookingByFalseStatus(user_id:number):Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/bookandtickfalse/"+user_id);
  }

  cancelTickets(booking_id:number):Observable<void>{
    return this.httpClient.put<void>("http://localhost:9090/changebookstatus/"+booking_id,booking_id);
  }

  findFlightByBookingId(booking_id:number):Observable<Flight>{
    return this.httpClient.get<Flight>("http://localhost:9090/flightbybooking/"+booking_id);
  }

  viewAllTickets():Observable<Ticket[]>{
    return this.httpClient.get<Ticket[]>("http://localhost:9090/viewallticks");
  }

  deleteOldTickets():Observable<void>{
    return this.httpClient.get<void>("http://localhost:9090/deleteoldtickets");
  }

  viewAllBookingAdmin():Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/viewadminbooking");
  }

  viewAllBookingAdminTrue():Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/viewadminbookingtrue");
  }

  viewAllBookingAdminFalse():Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/viewadminbookingfalse");
  }

  viewAllBookingAdminTrueDateG(user_id:number):Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/bookandticktruedate/"+user_id);
  }

  viewAllBookingAdminTrueDateS(user_id:number):Observable<Booking[]>{
    return this.httpClient.get<Booking[]>("http://localhost:9090/bookandtickfalsedate/"+user_id);
  }

  sendTicketDetails(emailDto:Emaildto):Observable<void>{
    return this.httpClient.post<void>("http://localhost:9090/sendticketmail",emailDto);
  }
}
