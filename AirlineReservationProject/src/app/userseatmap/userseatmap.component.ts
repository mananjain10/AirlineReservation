import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import $ from "jquery";
import { BookingServiceService } from '../booking-service.service';
import { FlightServiceService } from '../flight-service.service';
import { Seatmap } from '../seatmap';
import { Ticket } from '../ticket';

@Component({
  selector: 'app-userseatmap',
  templateUrl: './userseatmap.component.html',
  styleUrls: ['./userseatmap.component.css']
})
export class UserseatmapComponent implements OnInit {
  count: number;
  data=[];
  ids=[];
  t=[];
  j:number;
  k:number;
  isDisabled:boolean=false;
  trial:string;
  sts:string;
  seatmap=new Seatmap();
  tickets=new Ticket();
  userEmail:string;

  

  constructor(private flightService:FlightServiceService,private bookService:BookingServiceService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
    this.executeOnce();
    var aaa="1D";
    console.log(this.flightService.dateTdata);

     
    this.bookService.findTicketSeats(this.bookService.flightNoData).subscribe(
      viewSeats=>{
        
        
          
          console.log(viewSeats.toString());
          this.t=viewSeats;
          
          
          for(this.k=0;this.k<viewSeats.length;this.k++){
            this.seatmap=viewSeats[this.k];
            console.log(this.seatmap[1]);
            if(this.seatmap[1]==this.flightService.dateTdata){
              console.log("partyyyyy");
              console.log(viewSeats[this.k]);
              $('#'+viewSeats[this.k]).prop('disabled', true);
            }
            
          }
          
          
          //$('#'+viewSeats).prop('disabled', true);
          
        
        
      }
    );

  }

  executeOnce(){
    
    var component=this;
    $(function(){
      
      $('#selectSeats').off().on("click",()=>{
        console.log("aaa");
        //var val = [];
        $('input[type=checkbox]:checked').map(function(i){
          component.ids[i]=this.id;
        });
        component.bookService.seatData=component.ids;
        console.log(component.bookService.seatData);
        // for(component.j=0;component.j<component.ids.length;component.j++){
        //   component.seatmap.seatNo=component.ids[component.j];
        //   component.seatmap.flight_no=component.bookService.flightNoData;
        //   component.bookService.addSeat(component.seatmap).subscribe(
        //       adds=>{
        //         console.log(adds);
        //         //this.router.navigate(['/userPaymentPage']);
        //       }
        //     );
        // }
      });
    });

    
    
  }

  checked(){
   // $('input[type=checkbox]:checked').prop('disabled', 'disabled');
    this.count=this.bookService.noOfPassengersData;
    if($('input[type=checkbox]:checked').length==this.count){
      
      $('input[type=checkbox]:not(:checked)').prop('disabled', true);
      for(this.k=0;this.k<this.t.length;this.k++){
        this.seatmap=this.t[this.k];
        console.log(this.seatmap[1]);
        if(this.seatmap[1]==this.flightService.dateTdata){
          console.log("partyyyyy");
          console.log(this.t[this.k]);
          $('#'+this.t[this.k]).prop('disabled', true);
        }
        
      }
      
    }
    else if($('input[type=checkbox]:checked').length<this.count){
      
      $('input[type=checkbox]:not(:checked)').prop('disabled', false);
      for(this.k=0;this.k<this.t.length;this.k++){
        this.seatmap=this.t[this.k];
        console.log(this.seatmap[1]);
        if(this.seatmap[1]==this.flightService.dateTdata){
          console.log("partyyyyy");
          console.log(this.t[this.k]);
          $('#'+this.t[this.k]).prop('disabled', true);
        }
        
      }
      
 }

    console.log($('input[type=checkbox]:checked').length);
  }


  bookseats(){

    if($('input[type=checkbox]:checked').length<this.count){
      alert("select seats for all passengers");
    }
    else{
      this.executeOnce();

    

    setTimeout( ()=>{
      this.router.navigate(['/userPaymentPage']);
      }, 200)
    
    }
    
    
     
    
    


    // $("#selectSeats").on("click", function() {
    //   var checkedIds = $('input[type=checkbox]:checked').map(function() {
    //     return this.id;
    //   });
    //   console.log(checkedIds);
    //   // alert(checkedIds.join(", "));
    // });


  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
