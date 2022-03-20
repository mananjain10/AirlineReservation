import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from './flight';
import { SearchFlight } from './search-flight';

@Injectable({
  providedIn: 'root'
})
export class FlightServiceService {

  fromCityData:string;
  toCityData:string;
  dateTdata:string;

  constructor(private httpClient:HttpClient) { }

  addFlight(flight:Flight):Observable<Flight>{
    return this.httpClient.post<Flight>("http://localhost:9090/addflight",flight);
  }

  viewAllFlights():Observable<Flight[]>{
    return this.httpClient.get<Flight[]>("http://localhost:9090/viewallflights");
  }

  deleteFlight(flight_no:number):Observable<void>{
    return this.httpClient.delete<void>("http://localhost:9090/deleteflight/"+flight_no);
  }

  // searchFlight(searchF:SearchFlight):Observable<Flight[]>{
  //   return this.httpClient.post<Flight[]>("http://localhost:9090/searchflights",searchF);
  // }

  searchFlight(fromCity:string,toCity:string):Observable<Flight[]>{
    return this.httpClient.get<Flight[]>("http://localhost:9090/searchflight/"+fromCity+"/"+toCity);
  }

  updateFlight(flight:Flight):Observable<Flight>{
    return this.httpClient.put<Flight>("http://localhost:9090/updateflight",flight);
  }
}