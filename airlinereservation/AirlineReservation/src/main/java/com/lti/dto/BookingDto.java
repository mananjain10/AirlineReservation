package com.lti.dto;

import java.time.LocalDate;

public class BookingDto {
	int flight_no;
	int user_id;
	LocalDate bookDate;
	int noOfPassengers;
	public int getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(int flight_no) {
		this.flight_no = flight_no;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public LocalDate getBookDate() {
		return bookDate;
	}
	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	
}
