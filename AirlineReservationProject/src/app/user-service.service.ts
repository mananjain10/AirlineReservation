import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contactus } from './contactus';
import { Feedback } from './feedback';
import { Observable } from 'rxjs';
import { Loginuser } from './loginuser';
import { User } from './user';
import { Walletdto } from './walletdto';
import { Refundmail } from './refundmail';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient:HttpClient) { }

  registerUser(user:User):Observable<User>
  {
      return this.httpClient.post<User>("http://localhost:9090/registeruser",user);
  }

  validUser(loginUser:Loginuser):Observable<Boolean>
  {
    return this.httpClient.post<Boolean>("http://localhost:9090/loginuser",loginUser);
  }

  findUserById(userId:number):Observable<User>{
    return this.httpClient.get<User>("http://localhost:9090/users/"+userId);
  }

  findUserByEmail(email:string):Observable<User>{
    return this.httpClient.get<User>("http://localhost:9090/email/"+email);
  }

  updateUser(user:User,userId:number):Observable<User>{
    return this.httpClient.put<User>("http://localhost:9090/updateusers/"+userId,user);
  }

  validAdmin(loginUser:Loginuser):Observable<Boolean>
  {
    return this.httpClient.post<Boolean>("http://localhost:9090/loginadmin",loginUser);
  }

  getFeedback(feedback:Feedback):Observable<Feedback>{
    return this.httpClient.post<Feedback>("http://localhost:9090/getFeedback",feedback);
  }

  getContactus(contactus:Contactus):Observable<Contactus>{
    return this.httpClient.post<Contactus>("http://localhost:9090/getContactUs",contactus);
  }

  rechargeWallet(walletdto:Walletdto):Observable<Number>{
    return this.httpClient.post<Number>("http://localhost:9090/rechargewallet",walletdto);
  }

  payment(walletdto:Walletdto):Observable<Number>{
    return this.httpClient.post<Number>("http://localhost:9090/paywallet",walletdto); 
  }

  viewAllUsers():Observable<User[]>{
    return this.httpClient.get<User[]>("http://localhost:9090/viewallusers");
  }

  
  sendOtp(email:String):Observable<number>{
    return this.httpClient.get<number>("http://localhost:9090/send-otp?email="+email);
  } 

  validEmail(email:String):Observable<Boolean>
  {
    return this.httpClient.get<Boolean>("http://localhost:9090/validEmail?email="+email);
  } 

  resetPassword(email:string,password:string):Observable<void>{
    return this.httpClient.put<void>("http://localhost:9090/resetPassword/"+email+"/"+password,{email,password});
  }

  refundEmail(refundmail:Refundmail):Observable<void>{
    return this.httpClient.put<void>("localhost:9090/changebookstatus",refundmail);
  }
}
