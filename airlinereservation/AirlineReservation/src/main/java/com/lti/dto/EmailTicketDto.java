package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmailTicketDto {
	
	String email;
	int flight_no;
	int booking_id;
	String fromCity;
	String toCity;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(int flight_no) {
		this.flight_no = flight_no;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
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
	
	
	

}
