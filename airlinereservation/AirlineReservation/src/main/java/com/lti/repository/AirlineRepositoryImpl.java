package com.lti.repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.model.Admin;
import com.lti.model.Booking;
import com.lti.model.ContactUs;
import com.lti.model.Feedback;
import com.lti.model.Flight;
import com.lti.model.Passenger;
import com.lti.model.Ticket;
import com.lti.model.User;

@Repository
public class AirlineRepositoryImpl implements AirlineRepository {

	@PersistenceContext
	EntityManager em;
	
	Random random=new Random(1000);
	
	@Transactional
	public Flight addFlights(Flight flight) {
		Flight flight1 = em.merge(flight);
		em.close();
		return flight1;
	}

	@Transactional
	public void deleteFlights(int flightNo) {
		em.remove(em.find(Flight.class, flightNo));
		em.close();
	}

	@Transactional
	public Flight updateFlights(Flight flight) {
		Flight flight1 = em.merge(flight);
		em.close();
		return flight1;
	}

	public List<Flight> viewAllFlights() {
		String jpql = "select f from Flight f";
		Query query = em.createQuery(jpql);
		List<Flight> flights = query.getResultList();
		return flights;
	}

	public boolean loginAdmin(String aUserName, String aPassword) {
		String jpql = "select a from Admin a where a.aUserName=:aname and a.aPassword=:apass";
		Query query = em.createQuery(jpql);
		query.setParameter("aname", aUserName);
		query.setParameter("apass", aPassword);
		Admin admin = null;
		try {
			admin = (Admin) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (admin == null)
			return false;
		return true;
	}

	// User

	public boolean loginUser(String email, String password) {
		String jpql = "select u from User u where u.email=:uemail and u.password=:upass";
		Query query = em.createQuery(jpql);
		query.setParameter("uemail", email);
		query.setParameter("upass", password);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null)
			return false;
		return true;
	}

	@Transactional
	public User addUser(User user) {
		User user1 = em.merge(user);
		em.close();
		return user1;
	}
	
	@Transactional
	public Admin addAdmin(Admin admin) {
		Admin admin1 = em.merge(admin);
		em.close();
		return admin1;
	}


	@Transactional
	public User updateAUser(User user) {
		User user1 = em.merge(user);
		em.close();
		return user1;
	}

	@Transactional
	public void deleteUser(int user_id) {
		em.remove(em.find(User.class, user_id));
		em.close();
	}

	public User findUserById(int user_id) {
		return em.find(User.class, user_id);
	}

	public List<User> viewAllUsers() {
		String jpql = "select u from User u";
		Query query = em.createQuery(jpql);
		List<User> users = query.getResultList();
		return users;
	}

	public List<User> findUserByLastName(String lastName) {
		String jpql = "select u from User u where u.lastName=:lName";
		Query query = em.createQuery(jpql);
		query.setParameter("lName", lastName);

		List<User> users = query.getResultList();
		return users;
	}

	public User findUserByEmail(String email) {
		String jpql = "select u from User u where u.email=:uemail";
		Query query = em.createQuery(jpql);
		query.setParameter("uemail", email);

		User user =  (User) query.getSingleResult();
		return user;
	}

	public User findUserByBookingId(int booking_id) {
		Booking booking = em.find(Booking.class, booking_id);
		return booking.getUser();
	}

	public List<User> findUserByTicketId(int ticket_id) {
		String jqpl = "select u from User u where u.booking.ticket.ticket_id=:tId";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		query.setParameter("tId", ticket_id);
		List<User> users = query.getResultList();
		return users;
	}

	// flights
	public Flight findFlightByBookingID(int booking_id) {
		Booking booking = em.find(Booking.class, booking_id);
		return booking.getFlight();
	}

	public Flight findFlightsById(int flight_no) {
		return em.find(Flight.class, flight_no);
	}

	public List<Flight> findFlightsBySource(String source) {
		String jpql = "select f from Flight f where f.fromCity=:fsource";
		Query query = em.createQuery(jpql);
		query.setParameter("fsource", source);

		List<Flight> flights = query.getResultList();
		return flights;
	}

	public List<Flight> findFlightsByDestination(String destination) {
		String jpql = "select f from Flight f where f.toCity=:fdes";
		Query query = em.createQuery(jpql);
		query.setParameter("fdes", destination);
		List<Flight> flights = query.getResultList();
		return flights;
	}

	public List<Flight> findFlightsByDeptTime(LocalTime localTime) {
		String jpql = "select f from Flight f where f.depTime=:fdept";
		Query query = em.createQuery(jpql);
		query.setParameter("fdept", localTime);

		List<Flight> flights = query.getResultList();
		return flights;
	}

	public List<Flight> findFlightsByArrivalTime(LocalTime localTime) {
		String jpql = "select f from Flight f where f.arrTime=:farr";
		Query query = em.createQuery(jpql);
		query.setParameter("farr", localTime);

		List<Flight> flights = query.getResultList();
		return flights;
	}

	public List<Flight> findFlightsBySourceandDestination(String src, String des) {
		String jpql = "select f from Flight f where f.fromCity=:fsrc and f.toCity=:fdes";
		Query query = em.createQuery(jpql);
		query.setParameter("fsrc", src);
		query.setParameter("fdes", des);

		List<Flight> flights = query.getResultList();
		return flights;
	}
	
//	public List<Flight> searchFlightMain(String src, String des, LocalDate dateT) {
//		String jpql = "select f from Flight f where f.fromCity=:fsrc and f.toCity=:fdes and f.dateT=:fdate";
//		Query query = em.createQuery(jpql);
//		query.setParameter("fsrc", src);
//		query.setParameter("fdes", des);
//		query.setParameter("fdate", dateT);
//
//		List<Flight> flights = query.getResultList();
//		return flights;
//	}

	public List<Flight> findFlightsByDepTimeandArrTime(Time dep, Time arr) {
		String jpql = "select f from Flight f where f.depTime=:fdep and f.arrTime=:farr";
		Query query = em.createQuery(jpql);
		query.setParameter("fdep", dep);
		query.setParameter("farr", arr);

		List<Flight> flights = query.getResultList();
		return flights;
	}

	public String findSeatByTicketId(int ticket_id) {
		String jpql = "select t.seatNo from Ticket t where t.ticket_id=:tid";
		Query query = em.createQuery(jpql);
		query.setParameter("tid", ticket_id);

		return (String) query.getSingleResult();
	}

	public boolean findStatusByTicketId(int ticket_id) {
		String jpql = "select t.bookingStatus from Ticket t where t.ticket_id=:tid";
		Query query = em.createQuery(jpql);
		query.setParameter("tid", ticket_id);

		return (Boolean) query.getSingleResult();
	}

	public List<Ticket> findTicketByTravelDate(LocalDate travelDate) {
		String jpql = "select t from Ticket t where t.travelDate=:td";
		Query query = em.createQuery(jpql);
		query.setParameter("td", travelDate);
		return query.getResultList();

	}
	
	public List<Booking> viewBookingByUserAndTicketTrue(int user_id){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=1 and b.user.user_id=:uid";
		Query query = em.createQuery(jpql);
		query.setParameter("uid", user_id);
		return query.getResultList();

	}
	
	public List<Booking> viewBookingByUserAndTicketFalse(int user_id){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=0 and b.user.user_id=:uid";
		Query query = em.createQuery(jpql);
		query.setParameter("uid", user_id);
		return query.getResultList();

	}
	
	public List<Booking> viewBookingByUserAndTicketTrueAndDateG(int user_id){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=1 and b.user.user_id=:uid and trunc(sysdate)<t.travelDate";
		Query query = em.createQuery(jpql);
		query.setParameter("uid", user_id);
		return query.getResultList();

	}
	
	public List<Booking> viewBookingByUserAndTicketTrueAndDateS(int user_id){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=1 and b.user.user_id=:uid and trunc(sysdate)>t.travelDate";
		Query query = em.createQuery(jpql);
		query.setParameter("uid", user_id);
		return query.getResultList();

	}
	
	
	
	
	public List<Booking> viewAllBookingsAdmin(){
		String jpql = "select distinct b from Booking b join b.tickets t";
		Query query = em.createQuery(jpql);
		return query.getResultList();

	}
	
	public List<Booking> viewAllBookingsAdminTrue(){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=1";
		Query query = em.createQuery(jpql);
		return query.getResultList();

	}
	
	public List<Booking> viewAllBookingsAdminFalse(){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=0";
		Query query = em.createQuery(jpql);
		return query.getResultList();

	}
	
	@Transactional
	public String changeBookingStatus(int booking_id){
		String jpql="update Ticket t set t.bookingStatus=0 where t.booking.booking_id=:bid";
		Query query=em.createQuery(jpql);
		query.setParameter("bid", booking_id);
		
		int updatedRows=query.executeUpdate();
		if(updatedRows>0){
			return "0";
		}
		return "update failed";
	}
	
	@Transactional
	public String oldBookingStatus(){
		String jpql="update Ticket t set t.bookingStatus=0 where trunc(sysdate)>t.travelDate";
		Query query=em.createQuery(jpql);
		
		int updatedRows=query.executeUpdate();
		if(updatedRows>0){
			return "yo";
		}
		return "update failed";
	}
	
	public List<Booking> viewBookingByStatus(){
		String jpql = "select distinct b from Booking b join b.tickets t where t.bookingStatus=1";
		Query query = em.createQuery(jpql);
		return query.getResultList();

	}

	public List<Ticket> findTicketsByBookingId(int book_id) {
		String jpql = "select t from Ticket t where t.booking.booking_id=:bid";
		Query query = em.createQuery(jpql);
		query.setParameter("bid", book_id);

		List<Ticket> tickets = query.getResultList();
		return tickets;
	}
	
	public Passenger findPassengerByTicketId(int ticket_id) {
		Ticket ticket = em.find(Ticket.class, ticket_id);
		return ticket.getPassenger();
	}
	
	public List<Passenger> findPassengerByBookingId(int booking_id) {
		List<Passenger> passengers=new ArrayList<Passenger>();
		Booking booking=findBookingById(booking_id);
		for(Ticket tic : booking.getTickets()) {
			passengers.add(tic.getPassenger());
		}
		return passengers;
	}

	public List<Ticket> viewAllTickets() {
		String jpql = "select t from Ticket t";
		Query query = em.createQuery(jpql);
		List<Ticket> tickets = query.getResultList();
		return tickets;
	}

	public List<Ticket> viewAllTicketsByFlightNumber(int fid) {
		String jpql = "select t from Ticket t where t.flight.flight_no=:fid";
		Query query = em.createQuery(jpql);
		query.setParameter("fid", fid);
		List<Ticket> tickets = query.getResultList();
		return tickets;
	}
	
	public List<Ticket> viewAllTicketsByFlightNumberS(int fid) {
		String jpql = "select t.seatNo,t.travelDate from Ticket t where t.flight.flight_no=:fid";
		Query query = em.createQuery(jpql);
		query.setParameter("fid", fid);
		List<Ticket> tickets = query.getResultList();
		return tickets;
	}

	public Booking findBookingById(int bookId) {
		return em.find(Booking.class, bookId);
	}
	
	public Passenger findPassengerById(int passenger_id) {
		return em.find(Passenger.class, passenger_id);
	}

	public List<Booking> viewAllBookings() {
		String jpql = "select b from Booking b";
		Query query = em.createQuery(jpql);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	@Transactional
	public Ticket bookTicket(Ticket ticket) {
		Ticket ticket1 = em.merge(ticket);
		return ticket1;
	}

	@Transactional
	public Booking userDoesBooking(Booking booking) {
		Booking booking1 = em.merge(booking);
		return booking1;
	}

	@Transactional
	public Passenger addPassenger(Passenger passenger) {
		Passenger passengerPersist = em.merge(passenger);
		return passengerPersist;
	}
	
	@Transactional
	public Feedback getFeedback(Feedback feedback) {
		Feedback feedback1 = em.merge(feedback);
		em.close();
		return feedback1;
	}

	
	@Transactional
	public ContactUs getcontactUs(ContactUs contactus) {
		ContactUs contactus1 = em.merge(contactus);
		em.close();
		return contactus1;
	}
	

	@Override
	public List<Ticket> findSeatsByFlight(int flight_no) {
		
		return null;
	}
	
	@Transactional
	public double rechargeUserWallet(int userId, double wallet) {
		User user=em.find(User.class, userId);
		user.setWallet(user.getWallet()+wallet);
		return user.getWallet();
	}
	
	@Transactional
	public double payUserWallet(int userId, double wallet) {
		User user=em.find(User.class, userId);
		user.setWallet(user.getWallet()-wallet);
		return user.getWallet();
	}

	
	@Transactional 
	  public int generateOtp(String email) {
	  System.out.println("Email "+email); //generating otp of 4 Digits
	  
	  int otp = random.nextInt(999999);
	  
	 return otp; 
	 }
	  
	 /* 
	 * @Transactional public String verifyOtp(String email) { String msg =
	 * "Successfull!!"; return msg; }
	 */
	
	  @Transactional
	  public void resetPassword(String email, String password) {
		  User user = findUserByEmail(email);
		  user.setPassword(password);
		  em.merge(user);
	  }
	  
	  public boolean validEmail(String email) {
			String jpql = "select u from User u where u.email=:uemail";
			Query query = em.createQuery(jpql);
			query.setParameter("uemail", email);
			User user = null;
			try {
				user = (User) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user == null)
				return false;
			return true;
		}
}
