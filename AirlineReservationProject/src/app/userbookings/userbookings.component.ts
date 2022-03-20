import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { Booking } from '../booking';
import { BookingServiceService } from '../booking-service.service';
import { Ticket } from '../ticket';
import { UserServiceService } from '../user-service.service';
import { Walletdto } from '../walletdto';

@Component({
  selector: 'app-userbookings',
  templateUrl: './userbookings.component.html',
  styleUrls: ['./userbookings.component.css']
})
export class UserbookingsComponent implements OnInit {

  constructor(private bookService:BookingServiceService,private router:Router,private userService:UserServiceService) { }

  bookings:Booking[];
  bookings1:Booking[];
  userId:number;
  ticket=new Ticket();
  tickets:Ticket[];
  walletdto=new Walletdto();
  refundMoney:number;
  tprice:number;
  nofpass:number;
  remail:string;
  i:number;
  

  ngOnInit(): void {
    this.remail=localStorage.getItem("userEmail");
    
    if(this.remail==null){
      this.router.navigate(['/loginPage'])
    }
    
    this.userId=Number(localStorage.getItem("userId"));
    this.walletdto.user_id=Number(localStorage.getItem("userId"));
    this.bookService.viewAllBookingAdminTrueDateG(this.userId).subscribe(
      viewTrue=>{
        this.bookings=viewTrue;       
      }
    );
    this.bookService.viewAllBookingAdminTrueDateS(this.userId).subscribe(
      viewTrue=>{
        this.bookings=viewTrue;       
      }
    );
  }

  cancelticket(bookingId:number){

   
    this.bookService.findBookinfById(bookingId).subscribe(
      findBook=>{
        this.nofpass=findBook.noOfPassengers;
        console.log(this.nofpass);
      }
    );
    this.bookService.findFlightByBookingId(bookingId).subscribe(
      findFlight=>{
        this.tprice=findFlight.ticketPrice;
        console.log(this.tprice);
      }
    );
    
      

      
      

      setTimeout( ()=>{
        this.walletdto.wallet=this.nofpass*this.tprice;      
      
        console.log(this.walletdto.wallet);
        this.userService.rechargeWallet(this.walletdto).subscribe(
          recharge=>{
            console.log(recharge);
          }
        );

        // this.userService.refundEmail(bookingId,this.remail).subscribe(
        //   refMail=>{
        //     console.log(refMail);
        //     location.reload();
        //   }
        // );

        this.bookService.cancelTickets(bookingId).subscribe(
          cancelbook=>{
            alert("ticket cancelled");
            console.log(cancelbook);
            location.reload();
          }
        );
        
        }, 500)

        
    
  }

  viewticket(bookingId:number){
    this.bookService.bookingIdForViewTicketData=bookingId;
    this.router.navigate(['/userViewTicketPage'])
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
