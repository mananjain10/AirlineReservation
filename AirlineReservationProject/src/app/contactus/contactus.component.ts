import { Component, OnInit } from '@angular/core';
import { Contactus } from "../contactus";
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {

  contactus:Contactus=new Contactus();
  constructor(private service:UserServiceService) { }

  ngOnInit(): void {
  }

sendMsg(contactusForm){
  if(contactusForm.valid){
    console.log(this.contactus);
    this.service.getContactus(this.contactus).subscribe(
      contact => 
      console.log(contact)
      //this.contactus = contact

      );
    alert("We will get back to you shortly");
  }
  else{
    alert("Please fill details again.")
  }
}
}
