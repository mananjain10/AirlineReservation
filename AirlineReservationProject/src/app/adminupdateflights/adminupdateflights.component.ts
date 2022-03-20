import { Component, OnInit } from '@angular/core';
import { Flight } from "../flight";
import { FlightServiceService } from "../flight-service.service";

@Component({
  selector: 'app-adminupdateflights',
  templateUrl: './adminupdateflights.component.html',
  styleUrls: ['./adminupdateflights.component.css']
})
export class AdminupdateflightsComponent implements OnInit {

  flight:Flight=new Flight();

  constructor(private service:FlightServiceService) { }

  ngOnInit(): void {
  }

  updateFlight():void{
    this.service.updateFlight(this.flight).subscribe(
      flightPersisted=>{
        console.log(flightPersisted);
        location.reload();
      }
    );
  }

}
