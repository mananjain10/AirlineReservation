import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from '../flight';
import { FlightServiceService } from '../flight-service.service';

@Component({
  selector: 'app-adminviewallflights',
  templateUrl: './adminviewallflights.component.html',
  styleUrls: ['./adminviewallflights.component.css']
})
export class AdminviewallflightsComponent implements OnInit {

  flights:Flight[];
 
  constructor(private service:FlightServiceService,private router:Router) { }

  ngOnInit(): void {
    this.service.viewAllFlights().subscribe(
      flightData=>{
        this.flights=flightData;
      }
    );
  }

  deleteFlight(flight_no:number):void{
    
    this.service.deleteFlight(flight_no).subscribe(
      del=>{
        console.log(del);
        location.reload();
      }
    );

  }

}
