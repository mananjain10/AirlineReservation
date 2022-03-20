import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Flight } from '../flight';
import { FlightServiceService } from '../flight-service.service';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent implements OnInit {

  flights:Flight[];
  constructor(private flightservice:FlightServiceService) { }

  ngOnInit(): void {
    this.flightservice.viewAllFlights().subscribe(
      vf=>{
        this.flights=vf;
      }
    );
  }

  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: true,
    navSpeed: 200,
    navText: [ ],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 3
      }
    },
    nav: true
  }

}
