import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingServiceService } from '../booking-service.service';
import { Passenger } from "../passenger";

@Component({
  selector: 'app-userpassengerdetails',
  templateUrl: './userpassengerdetails.component.html',
  styleUrls: ['./userpassengerdetails.component.css']
})
export class UserpassengerdetailsComponent implements OnInit {

  passenger= new Passenger();
  dataArray=[];
  i:number=0;
  userEmail:string;
  constructor(private bookService:BookingServiceService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
    this.passenger=new Passenger();
    this.dataArray.push(this.passenger);
  }

  addForm(){
    if(this.dataArray.length < this.bookService.noOfPassengersData){
      this.passenger=new Passenger();
      this.dataArray.push(this.passenger);
    }
    else{
      console.log("No more passengers can be added");
    }
    
  }

  removeForm(index){
    this.dataArray.splice(index);
  }
  onSubmit(passForm){
    if(passForm.valid){
    if(this.dataArray.length==this.bookService.noOfPassengersData){
      console.log("okok");
      this.bookService.passengerIdData=50001;
      for(this.i=0;this.i<this.bookService.noOfPassengersData;this.i++){
        this.passenger=this.dataArray[this.i];

        console.log(this.passenger);
        this.bookService.addPassenger(this.passenger).subscribe(
          addPass=>{
            console.log(addPass);     
            
            if(addPass.passenger_id >= this.bookService.passengerIdData){
              this.bookService.passengerIdData=addPass.passenger_id;
              console.log("ppid"+this.bookService.passengerIdData);
            }            
            
            console.log("ppid"+this.bookService.passengerIdData);
            console.log("pid"+addPass.passenger_id);       
            if(addPass){
              this.router.navigate(['/userSeatMapPage']);
            }
          }
        );
      }
    }
    else{
      alert("enter all passenger details")
    }}
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

}
