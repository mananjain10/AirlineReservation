import { Component, OnInit } from '@angular/core';
import  jspdf from 'jspdf'
import html2canvas from 'html2canvas'
import { BookingServiceService } from '../booking-service.service';
import { Flight } from '../flight';
import { Passenger } from '../passenger';
import { Ticket } from '../ticket';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userviewticket',
  templateUrl: './userviewticket.component.html',
  styleUrls: ['./userviewticket.component.css']
})
export class UserviewticketComponent implements OnInit {

  flight=new Flight();
  tickets:Ticket[];
  passengers:Passenger[];
  userEmail:string;

  
  constructor(public bookService:BookingServiceService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
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


  public downloadPDF(){
    var data = document.getElementById('table');
    html2canvas(data,{scrollY: -window.scrollY, scale: 1}).then(canvas => {
    //console.log(canvas)
    var imgWidth = 208;
    var pageHeight=295;
    var imgHeight = canvas.height * imgWidth / canvas.width;
    var heightLeft = imgHeight;
    
    var contentDataURL = canvas.toDataURL('image/png')
    let pdf = new jspdf('p' , 'mm', 'a4');
    var position=0;
    
    pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
    pdf.save('ticket.pdf'); 
    });                                                                                   
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
