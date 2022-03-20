import { Component, OnInit } from '@angular/core';
import { Feedback } from '../feedback';
import { UserServiceService } from '../user-service.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css','./bootstrap.min.css']
})
export class HomeComponent implements OnInit {


  feedback:Feedback=new Feedback();
  constructor(private service:UserServiceService) { }

  ngOnInit(): void {
  }

  sendFeedback(feedbackForm){
    if(feedbackForm.valid){
      console.log(this.feedback);
      this.service.getFeedback(this.feedback).subscribe(
        feeds => this.feedback = feeds
      );
      alert("Thank you so much for your valuable feedback");
    }
    else{
      alert("Please fill details again.")
    }
  }
}
