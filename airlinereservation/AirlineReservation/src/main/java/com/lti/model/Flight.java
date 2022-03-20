package com.lti.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_flight1")
public class Flight {
	
	@Id
	@SequenceGenerator(name="flight_seq",initialValue=20001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="flight_seq")
	int flight_no;
	
	String flightName;
	String fromCity;
	String toCity;
	LocalTime depTime;
	LocalTime arrTime;
	//LocalDate dateT;
	double ticketPrice;
	int capacity;
	
	@OneToMany(mappedBy="flight",cascade=CascadeType.ALL)
	List<Ticket> tickets;
	
	@OneToMany(mappedBy="flight",cascade=CascadeType.ALL)
	List<Booking> bookings;
	

	public int getFlight_no() {
		return flight_no;
	}

	public void setFlight_no(int flight_no) {
		this.flight_no = flight_no;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public LocalTime getDepTime() {
		return depTime;
	}

	public void setDepTime(LocalTime depTime) {
		this.depTime = depTime;
	}

	public LocalTime getArrTime() {
		return arrTime;
	}

	public void setArrTime(LocalTime arrTime) {
		this.arrTime = arrTime;
	}
	

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	


	
	
	
	
	
	

}
