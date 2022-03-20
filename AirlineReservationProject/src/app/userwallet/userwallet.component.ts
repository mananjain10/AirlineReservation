import { Component, OnInit } from '@angular/core';
import { Wallet } from "../wallet";
import { User } from '../user';
import { UserServiceService } from '../user-service.service';
import { Walletdto } from '../walletdto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userwallet',
  templateUrl: './userwallet.component.html',
  styleUrls: ['./userwallet.component.css']
})
export class UserwalletComponent implements OnInit {

  walletobj:Wallet=new Wallet();

  user:User=new User();
  userId:number;
  wallet:DoubleRange;
  walletdto=new Walletdto();
  userEmail:string;

  constructor(private service:UserServiceService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=(localStorage.getItem("userEmail"));
    if(this.userEmail==null){
      this.router.navigate(['/loginPage'])
    }
    this.userId=Number(localStorage.getItem("userId"));
    this.service.findUserById(this.userId).subscribe(
      wal=>{
        this.user.wallet=wal.wallet;
      }
    );
  }

  rechargeWallet(walletForm){
    
    if(walletForm.valid){
      
      this.walletdto.user_id=Number(localStorage.getItem("userId"));
      this.walletdto.wallet=this.user.wallet;
      this.service.rechargeWallet(this.walletdto).subscribe(
        newWallet=>{
          console.log(newWallet);
          alert("Wallet has been Recharged..");
          location.reload();
        }
      );
      alert("Form Submitted... Wait for Registered Email");
    }
    else{
      alert("Please fill details again.")
    }
  }

  logout():void{
    console.log("clearing.....")
    localStorage.clear();
  }

  
}