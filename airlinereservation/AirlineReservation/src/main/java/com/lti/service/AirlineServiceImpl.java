package com.lti.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.BookingDto;
import com.lti.dto.EmailTicketDto;
import com.lti.dto.TicketDto;
import com.lti.model.Admin;
import com.lti.model.Booking;
import com.lti.model.ContactUs;
import com.lti.model.Feedback;
import com.lti.model.Flight;
import com.lti.model.Passenger;
import com.lti.model.Ticket;
import com.lti.model.User;
import com.lti.repository.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService {
	
	@Autowired
	AirlineRepository airlineDao;
	
	@Autowired
	EmailService emailService;

	public Flight addFlights(Flight flight) {
		
		return airlineDao.addFlights(flight);
	}

	public void deleteFlights(int flightNo) {
		airlineDao.deleteFlights(flightNo);

	}

	public Flight updateFlights(Flight flight) {
		
		return airlineDao.updateFlights(flight);
	}

	public List<Flight> viewAllFlights() {
		
		return airlineDao.viewAllFlights();
	}

	public boolean loginAdmin(String aUserName, String aPassword) {
		
		return airlineDao.loginAdmin(aUserName, aPassword);
	}

	public boolean loginUser(String email, String password) {
		User user = airlineDao.findUserByEmail(email);
		if(user != null && BCrypt.checkpw(password, user.getPassword())){
			//return airlineDao.loginUser(email,user.getPassword());
			return true;
		}else {
			return false;
		}
		
		
	}

	public User addUser(User user) {
		String subject="Registration confirmation";
		String text="Hi "+user.getFirstName()+"! You have successfully registered. We promise to give you our best no matter what.";
		String fileToattach="D:\\Airline Project\\ang\\mailsender.jpeg";
		emailService.sendEmailWithPicForNewRegistration(user.getEmail(),text,subject,fileToattach);
		user.setPassword(encryptPassword(user.getPassword()));
		return airlineDao.addUser(user);
	}
	
	public String sendTicketDetails(EmailTicketDto emailTicketDto) {
		String subject="Flight Details";
		String text="Hi! \n Thank you for flying with us."+"\n Booking id: "+emailTicketDto.getBooking_id()+"\n Flight Number: "+emailTicketDto.getFlight_no()+"\n Source: "+emailTicketDto.getFromCity()+"\n Destination: "+emailTicketDto.getToCity();
		emailService.sendEmailForTicket(emailTicketDto.getEmail(), text, subject);
		return "Email sent";
	}
	
	public Admin addAdmin(Admin admin) {
		return airlineDao.addAdmin(admin);
	}
	
	private String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public User updateAUser(User user) {
		
		return airlineDao.updateAUser(user);
	}

	public void deleteUser(int user_id) {
		
		airlineDao.deleteUser(user_id);
	}

	public User findUserById(int user_id) {
		
		return airlineDao.findUserById(user_id);
	}

	public List<User> viewAllUsers() {
		
		return airlineDao.viewAllUsers();
	}

	public List<User> findUserByLastName(String lastName) {
		
		return airlineDao.findUserByLastName(lastName);
	}

	public User findUserByEmail(String email) {
		
		return airlineDao.findUserByEmail(email);
	}

	public User findUserByBookingId(int booking_id) {
		
		return airlineDao.findUserByBookingId(booking_id);
	}

	public List<User> findUserByTicketId(int ticket_id) {
		
		return airlineDao.findUserByTicketId(ticket_id);
	}

	public Flight findFlightsById(int flight_no) {
		
		return airlineDao.findFlightsById(flight_no);
	}

	public List<Flight> findFlightsBySource(String source) {
		
		return airlineDao.findFlightsBySource(source);
	}

	public List<Flight> findFlightsByDestination(String destination) {
		
		return airlineDao.findFlightsByDestination(destination);
	}

	public List<Flight> findFlightsByDeptTime(LocalTime localTime) {
		
		return airlineDao.findFlightsByDeptTime(localTime);
	}

	public List<Flight> findFlightsByArrivalTime(LocalTime localTime) {
		
		return airlineDao.findFlightsByArrivalTime(localTime);
	}

	public List<Flight> findFlightsBySourceandDestination(String src, String des) {
		
		return airlineDao.findFlightsBySourceandDestination(src, des);
	}
	
//	public List<Flight> searchFlightMain(String src, String des, LocalDate dateT){
//		return airlineDao.searchFlightMain(src, des, dateT);
//	}

	public List<Flight> findFlightsByDepTimeandArrTime(Time dep, Time arr) {
		
		return airlineDao.findFlightsByDepTimeandArrTime(dep, arr);
	}

	public String findSeatByTicketId(int ticket_id) {
		
		return airlineDao.findSeatByTicketId(ticket_id);
	}

	public boolean findStatusByTicketId(int ticket_id) {
		
		return airlineDao.findStatusByTicketId(ticket_id);
	}

	public List<Ticket> findTicketByTravelDate(LocalDate travelDate) {
		
		return airlineDao.findTicketByTravelDate(travelDate);
	}

	public List<Ticket> findTicketsByBookingId(int book_id) {
		
		return airlineDao.findTicketsByBookingId(book_id);
	}

	public List<Ticket> viewAllTickets() {
		
		return airlineDao.viewAllTickets();
	}

	public List<Ticket> viewAllTicketsByFlightNumber(int fid) {
		
		return airlineDao.viewAllTicketsByFlightNumber(fid);
	}

	public Booking findBookingById(int bookId) {
		
		return airlineDao.findBookingById(bookId);
	}

	public List<Booking> viewAllBookings() {
		
		return airlineDao.viewAllBookings();
	}

	public Ticket bookTicket(TicketDto ticketDto) {
		int flightNo=ticketDto.getFlight_no();
		Flight flight=airlineDao.findFlightsById(flightNo);
		int bookingId=ticketDto.getBooking_id();
		Booking booking=airlineDao.findBookingById(bookingId);
		int passengerId=ticketDto.getPassenger_id();
		Passenger passenger=airlineDao.findPassengerById(passengerId);
		
		Ticket ticket=new Ticket();
		ticket.setBooking(booking);
		ticket.setFlight(flight);
		ticket.setPassenger(passenger);
		ticket.setSeatNo(ticketDto.getSeatNo());
		ticket.setTravelDate(ticketDto.getTravelDate());
		ticket.setBookingStatus(ticketDto.isBookingStatus());
		Ticket ticketSaved=airlineDao.bookTicket(ticket);
		return airlineDao.bookTicket(ticketSaved);
	}

	public Booking userDoesBooking(BookingDto bookingDto) {
		int flightNo=bookingDto.getFlight_no();
		int userId=bookingDto.getUser_id();
		Flight flight=airlineDao.findFlightsById(flightNo);
		User user=airlineDao.findUserById(userId);
		
		Booking booking=new Booking();
		booking.setBookDate(bookingDto.getBookDate());
		booking.setNoOfPassengers(bookingDto.getNoOfPassengers());
		booking.setFlight(flight);
		booking.setUser(user);
		Booking bookingDone=airlineDao.userDoesBooking(booking);
		
		return bookingDone;
	}

	
	public Passenger addPassenger(Passenger passenger) {
		
		return airlineDao.addPassenger(passenger);
	}
	
	public Passenger findPassengerByTicketId(int ticket_id) {
		return airlineDao.findPassengerByTicketId(ticket_id);
	}
	
	public List<Passenger> findPassengerByBookingId(int booking_id){
		return airlineDao.findPassengerByBookingId(booking_id);
	}

	public Feedback getFeedback(Feedback feedback) {
        String subject="Feedback Received";
        String text="Hi "+feedback.getName()+"! Thanks for your valuable feedback!!!.";
        emailService.sendEmailForFeedback(feedback.getEmail(),text,subject);
        return airlineDao.getFeedback(feedback);
    }

	@Override
	public ContactUs getcontactUs(ContactUs contactus) {
		String subject="Query/Concern Received";
		String text="Hi "+contactus.getFirstName()+"! We received your email and surely will look into this matter at the earliest. Request you to wait until our team tries to get in touch with you.";
		emailService.sendEmailForFeedback(contactus.getEmail(),text,subject);
		return airlineDao.getcontactUs(contactus);
	}
	

	@Override
	public List<Ticket> viewAllTicketsByFlightNumberS(int fid) {
		// TODO Auto-generated method stub
		return airlineDao.viewAllTicketsByFlightNumberS(fid);
	}
	
	public List<Booking> viewBookingByUserAndTicketTrue(int user_id){
		return airlineDao.viewBookingByUserAndTicketTrue(user_id);
	}
	public List<Booking> viewBookingByUserAndTicketFalse(int user_id){
		return airlineDao.viewBookingByUserAndTicketFalse(user_id);
	}
	public List<Booking> viewBookingByStatus(){
		return airlineDao.viewBookingByStatus();
	}
	
	public double rechargeUserWallet(int userId, double wallet) {
		return airlineDao.rechargeUserWallet(userId, wallet);
	}
	
	public String changeBookingStatus(int booking_id) {
		
		return airlineDao.changeBookingStatus(booking_id);
}
	
	public Flight findFlightByBookingID(int booking_id) {
		return airlineDao.findFlightByBookingID(booking_id);
	}
	
	public String oldBookingStatus() {
		return airlineDao.oldBookingStatus();
	}
	
	public double payUserWallet(int userId, double wallet) {
		return airlineDao.payUserWallet(userId, wallet);
				
	}
	
	public List<Booking> viewAllBookingsAdmin(){
		return airlineDao.viewAllBookingsAdmin();
	}
	
	public List<Booking> viewAllBookingsAdminTrue(){
		return airlineDao.viewAllBookingsAdminTrue();
	}
	
	public List<Booking> viewAllBookingsAdminFalse()
	{
		return airlineDao.viewAllBookingsAdminFalse();
	}
	
	public int generateOtp(String email) { 
		 
		 return airlineDao.generateOtp(email);
	 }

	@Override
	public void resetPassword(String email, String password) {
		airlineDao.resetPassword(email,encryptPassword(password));
		
	}
	
public boolean validEmail(String email) {
		
		return airlineDao.validEmail(email);
	}

	public List<Booking> viewBookingByUserAndTicketTrueAndDateG(int user_id){
		return airlineDao.viewBookingByUserAndTicketTrueAndDateG(user_id);
	}
	
	public List<Booking> viewBookingByUserAndTicketTrueAndDateS(int user_id){
		return airlineDao.viewBookingByUserAndTicketTrueAndDateS(user_id);
	}

}
