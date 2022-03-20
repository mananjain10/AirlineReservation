import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingServiceService } from '../booking-service.service';
import { Flight } from '../flight';
import { FlightServiceService } from '../flight-service.service';
import { Loginuser } from '../loginuser';
import { Ticket } from '../ticket';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';
import { Wallet } from '../wallet';
import { Walletdto } from '../walletdto';

@Component({
  selector: 'app-userpayment',
  templateUrl: './userpayment.component.html',
  styleUrls: ['./userpayment.component.css']
})
export class UserpaymentComponent implements OnInit {

  ticket=new Ticket();
  user=new User();
  flight=new Flight();
  loginuser=new Loginuser();
  i:number;
  enoughmoney:number;
  wal:number;
  userId:number;
  wallet=new Wallet();
  walletdto=new Walletdto();
  userEmail:string;

  constructor(private userService:UserServiceService, private bookService:BookingServiceService,private flightService:FlightServiceService,private router:Router) { }

  ngOnInit(): void {
   console.log( this.bookService.seatData);
   this.loginuser.email=localStorage.getItem("userEmail");
   this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }

   this.userId=Number(localStorage.getItem("userId"));
    this.userService.findUserById(this.userId).subscribe(
      needWallet=>{
        this.user=needWallet;
      }
    );
    this.bookService.findFlightById(this.bookService.flightNoData).subscribe(
      ticPrice=>{
        this.flight=ticPrice;
      }
    );

    this.walletdto.user_id=Number(localStorage.getItem("userId"));
  }

  payment():void{
    this.enoughmoney=this.bookService.noOfPassengersData*this.flight.ticketPrice;
    this.wal=Number(this.user.wallet);
    this.userService.validUser(this.loginuser).subscribe(
      isValid=>{
        if(isValid)
        {
          if((this.wal-this.enoughmoney)<0){
            alert("not enough money");
          }
          else{
            this.walletdto.wallet=this.enoughmoney;
            this.userService.payment(this.walletdto).subscribe(
              walpay=>{
                if(walpay)
                  console.log(this.user.wallet);
              }
            );

            for(this.i=0;this.i<this.bookService.noOfPassengersData;this.i++){
              this.ticket.seatNo=this.bookService.seatData[this.i];
              this.ticket.travelDate=this.flightService.dateTdata;
              this.ticket.booking_id=this.bookService.bookingIdData;
              this.ticket.flight_no=this.bookService.flightNoData;
              this.ticket.passenger_id=this.bookService.passengerIdData-this.i;
              console.log(this.bookService.passengerIdData-this.i)
              this.ticket.bookingStatus=true;
              this.bookService.addTicket(this.ticket).subscribe(
                addTick=>{
                  console.log(this.bookService.bookingIdData);
                  console.log(this.bookService.flightNoData);
                  console.log(this.flightService.dateTdata);
                  if(addTick){
                    
                    this.router.navigate(['/userTicketPrintPage']);
                    
                  }
                  
                  
                  console.log(addTick);
                }
              );                    
            }
          }          
        }
        
        else{
          alert("invalid password");
        }
      }
    );
    
    

    

}

logout():void{
  console.log("clearing.....")
  localStorage.clear();
}
}
