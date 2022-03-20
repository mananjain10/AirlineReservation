package com.lti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lti.dto.AdminDto;
import com.lti.dto.BookingDto;
import com.lti.dto.EmailTicketDto;
import com.lti.dto.LoginDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.TicketDto;
import com.lti.dto.WalletDto;
import com.lti.model.Admin;
import com.lti.model.Booking;
import com.lti.model.ContactUs;
import com.lti.model.Feedback;
import com.lti.model.Flight;
import com.lti.model.Passenger;
import com.lti.model.Ticket;
import com.lti.model.User;
import com.lti.service.AirlineService;
import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping(value = "/registeruser")
	public User addorUpdateAUser(@RequestBody User user) {
		User userPersisted = airlineService.addUser(user);

		return userPersisted;
	}
	
	@PostMapping(value = "/loginuser")
	public boolean loginUser(@RequestBody LoginDto loginDto) {
		
		return airlineService.loginUser(loginDto.getEmail(),loginDto.getPassword());
	}
	
	@PostMapping(value = "/loginadmin")
	public boolean loginAdmin(@RequestBody LoginDto loginDto) {
		
		return airlineService.loginAdmin(loginDto.getEmail(),loginDto.getPassword());
	}
	
	@PostMapping(value="/addadmin")
	public Admin addAdmin(@RequestBody Admin admin) {
		return airlineService.addAdmin(admin);
	}
	
	@PutMapping(value="/updateuser")
	public User updateAUser(@RequestBody User user) {
		User userPersisted = airlineService.updateAUser(user);

		return userPersisted;
	}
	
	@PutMapping(value="/updateusers/{user_id}")
	public User updateAUser1(@RequestBody User user,@PathVariable("user_id") int user_id) {
		user.setUser_id(user_id);
		User userPersisted = airlineService.updateAUser(user);

		return userPersisted;
	}
	
	@DeleteMapping(value="/deleteuser/{user_id}")
	public void deleteUser(@PathVariable("user_id") int user_id) {
		airlineService.deleteUser(user_id);
	}
	
	@GetMapping(value="/users/{user_id}")
	public User findUserById(@PathVariable("user_id") int user_id) {
		User userPersisted = airlineService.findUserById(user_id);
		return userPersisted;
	}
	
	@GetMapping(value="/email/{email}")
	public User findUserByEmail(@PathVariable("email") String email) {
		User userPersisted = airlineService.findUserByEmail(email);
		return userPersisted;
	}
	
	@GetMapping(value="/viewallusers")
	public List<User> viewAllUsers() {
		List<User> userPersisted = airlineService.viewAllUsers();
		return userPersisted;
	}
	

	@GetMapping(value="/username/{lastName}")
	public List<User> findUserByLastName(@PathVariable("lastName") String lastName) {
		List<User> userPersisted = airlineService.findUserByLastName(lastName);
		return userPersisted;
	}
	
//	admin
	@PostMapping(value = "/addflight")
	public Flight addFlight(@RequestBody Flight flight) {
		Flight userPersisted = airlineService.addFlights(flight);

		return userPersisted;
	}
	
	@PutMapping(value = "/updateflight")
	public Flight updateFlight(@RequestBody Flight flight) {
		
		Flight flightPersist=airlineService.updateFlights(flight);

		return flightPersist;
	}
	
	
	
	@GetMapping(value="/viewallflights")
	public List<Flight> viewAllFlights(){
		List<Flight> flightPersist=airlineService.viewAllFlights();
		return flightPersist;
	}
	
	@DeleteMapping(value = "/deleteflight/{flight_no}")
	public void deleteFlight(@PathVariable("flight_no") int flight_no) {
		airlineService.deleteFlights(flight_no);
	}
	
	@GetMapping(value = "/searchforbooking/{flight_no}")
	public Flight findFlightById(@PathVariable("flight_no") int flight_no) {
		Flight findflight=airlineService.findFlightsById(flight_no);
		return findflight;
	}
	
	@GetMapping(value = "/searchticketforbooking/{booking_id}")
	public List<Ticket> findTicketsByBookingId(@PathVariable("booking_id") int booking_id){
		List<Ticket> findTickets=airlineService.findTicketsByBookingId(booking_id);
		return findTickets;
	}
	
	@GetMapping(value = "/searchpassforbooking/{ticket_id}")
	public Passenger findPassByTicketId(@PathVariable("ticket_id") int ticket_id) {
		Passenger pass=airlineService.findPassengerByTicketId(ticket_id);
		return pass;
	}
	
	@GetMapping(value = "/searchpassforbookings/{booking_id}")
	public List<Passenger> findPassByBookingId(@PathVariable("booking_id") int booking_id) {
		List<Passenger> pass=airlineService.findPassengerByBookingId(booking_id);
		return pass;
	}
	
	@GetMapping(value = "/bookandtick/{user_id}")
	public List<Booking> bookAndTick(@PathVariable("user_id") int user_id){
		List<Booking> bt=airlineService.viewBookingByUserAndTicketTrue(user_id);
		
		return bt;
	}
	
	@GetMapping(value = "/bookandtickfalse/{user_id}")
	public List<Booking> bookAndTickfalse(@PathVariable("user_id") int user_id){
		List<Booking> bt=airlineService.viewBookingByUserAndTicketFalse(user_id);
		
		return bt;
	}
	
	@GetMapping(value = "/bookandticktruedate/{user_id}")
	public List<Booking> bookAndTickTrueAndDateG(@PathVariable("user_id") int user_id){
		List<Booking> bt=airlineService.viewBookingByUserAndTicketTrueAndDateG(user_id);
		
		return bt;
	}
	
	@GetMapping(value = "/bookandtickfalsedate/{user_id}")
	public List<Booking> bookAndTickTrueAndDateS(@PathVariable("user_id") int user_id){
		List<Booking> bt=airlineService.viewBookingByUserAndTicketTrueAndDateS(user_id);
		
		return bt;
	}
	
	@PutMapping(value = "/changebookstatus/{booking_id}")
	public String changeBookStatus(@PathVariable("booking_id") int booking_id) {
		return airlineService.changeBookingStatus(booking_id);
	}
	
	
	@PostMapping(value = "/bookandstatus")
	public List<Booking> bookAndTick(){
		List<Booking> bt=airlineService.viewBookingByStatus();
		
		return bt;
	}
	
	@GetMapping(value = "/flightbybooking/{booking_id}")
	public Flight findFlightByBookId(@PathVariable("booking_id") int booking_id) {
		Flight fb=airlineService.findFlightByBookingID(booking_id);
		return fb;
	}
	
	@GetMapping(value = "/viewallticks")
	public List<Ticket> viewAllTickets(){
		return airlineService.viewAllTickets();
	}
	
	@GetMapping(value = "/deleteoldtickets")
	public String deleteOldTickets() {
		return airlineService.oldBookingStatus();
	}
	
//	@PostMapping(value = "/searchflights")
//	public List<Flight> searchFlightMain(@RequestBody SearchFlightDto searchFlightDto){
//		List<Flight> flights=airlineService.searchFlightMain(searchFlightDto.getFromCity(), searchFlightDto.getToCity(),searchFlightDto.getDateT());
//		return flights;
//	}
	
	
//	booking
	@PostMapping(value = "/booking")
	public Booking userDoesBooking(@RequestBody BookingDto bookingDto) {
		
		Booking bookPersist=airlineService.userDoesBooking(bookingDto);	
		
		return bookPersist;
	}
	
	@PostMapping(value = "/addTickets")
	public Ticket addTickets(@RequestBody TicketDto ticketDto) {
		
		Ticket ticketPersist=airlineService.bookTicket(ticketDto);	
		
		return ticketPersist;
	}
	
	
	
	@GetMapping(value="/searchflight/{fromCity}/{toCity}")
	public List<Flight> searchFlight(@PathVariable("fromCity") String fromCity, @PathVariable("toCity") String toCity){
		List<Flight> flights=airlineService.findFlightsBySourceandDestination(fromCity, toCity);
		return flights;
	}
	
	@PostMapping(value = "/addpassenger")
	public Passenger addPassenger(@RequestBody Passenger passenger) {
		Passenger passPersist=airlineService.addPassenger(passenger);
		return passPersist;
	}
	

	@PostMapping(value="/getFeedback")
	public Feedback getFeedback(@RequestBody Feedback feedback) {
		Feedback persistFeedback = airlineService.getFeedback(feedback);
		return persistFeedback;
	}
	
	@PostMapping(value = "/sendticketmail")
	public String sendTicketMail(@RequestBody EmailTicketDto emailTicketDto) {
		return airlineService.sendTicketDetails(emailTicketDto);
	}
	
	@PostMapping(value="/getContactUs")
	public ContactUs getContactUs(@RequestBody ContactUs contactus) {
		ContactUs persistContactus = airlineService.getcontactUs(contactus);
		return persistContactus;
	}
	
	
	@GetMapping(value = "/findSeat/{flight_no}")
	public List<Ticket> findSeats(@RequestBody @PathVariable("flight_no") int flight_no){
		List<Ticket> allseats=airlineService.viewAllTicketsByFlightNumber(flight_no);
		return allseats;
	}
	
	@GetMapping(value = "/findSeats/{flight_no}")
	public List<Ticket> findSeatsString(@RequestBody @PathVariable("flight_no") int flight_no){
		List<Ticket> allseats=airlineService.viewAllTicketsByFlightNumberS(flight_no);
		return allseats;
	}
	
	@PostMapping(value="/rechargewallet")
	public double rechargeUserWallet(@RequestBody WalletDto walletDto) {
		double walletPersisted = airlineService.rechargeUserWallet(walletDto.getUser_id(),walletDto.getWallet());

		return walletPersisted;
	}
	
	@PostMapping(value="/paywallet")
	public double payUserWallet(@RequestBody WalletDto walletDto) {
		double walletPersisted = airlineService.payUserWallet(walletDto.getUser_id(),walletDto.getWallet());

		return walletPersisted;
	}
	
	@GetMapping(value = "/findbybookid/{booking_id}")
	public Booking findBookingById(@PathVariable("booking_id") int booking_id) {
		Booking booking=airlineService.findBookingById(booking_id);
		return booking;
	}
	
	@GetMapping(value = "/viewadminbookingtrue")
	public List<Booking> viewAllBookingAdminT(){
		List<Booking> booking=airlineService.viewAllBookingsAdminTrue();
		return booking;
	}
	
	@GetMapping(value = "/viewadminbookingfalse")
	public List<Booking> viewAllBookingAdminF(){
		List<Booking> booking=airlineService.viewAllBookingsAdminFalse();
		return booking;
	}
	
	
	@PostMapping("/uploads")
	public Status upload(@RequestBody MultipartFile file) {
		String pdfUploadLocation = "d:/pics/";
		String fileName = file.getName();
		String targetFile = pdfUploadLocation + fileName;
		
		try {
			System.out.println("noo"+fileName);
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFile));
		} catch (IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Uploaded!");
		return status;
	}
	
	private String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	@GetMapping(value="/send-otp") 
	 public int generateOtp(@RequestParam("email") String email) { 
		 
		 int otp1=airlineService.generateOtp(email);
		 String subject="OTP sent successfully";
		 String text="Hi "+email+"! Your OTP for changing password is "+otp1; 
		 emailService.sendEmailForForgotPassword(email,text,subject);
	  
	  return otp1;
	  
	  }
	  
	 @PutMapping(value="/resetPassword/{email}/{password}")
	 public void resetPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
		 airlineService.resetPassword(email, password);
	 }
	
	 @GetMapping(value = "/validEmail")
		public boolean validEmail(@RequestParam("email") String email) {
			
			return airlineService.validEmail(email);
		}

}
