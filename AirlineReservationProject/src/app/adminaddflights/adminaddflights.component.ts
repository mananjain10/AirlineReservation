import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from "../flight";
import { FlightServiceService } from "../flight-service.service";

@Component({
  selector: 'app-adminaddflights',
  templateUrl: './adminaddflights.component.html',
  styleUrls: ['./adminaddflights.component.css']
})
export class AdminaddflightsComponent implements OnInit {

  flight:Flight=new Flight();

  constructor(private service:FlightServiceService,private router:Router) { }

  ngOnInit(): void {

    
  }

  addFlight():void{

    this.service.addFlight(this.flight).subscribe(
      flightPersisted=>{
        console.log(flightPersisted);
        this.router.navigate(['/viewAllFlightsPage']);
      }
    );
  }

}