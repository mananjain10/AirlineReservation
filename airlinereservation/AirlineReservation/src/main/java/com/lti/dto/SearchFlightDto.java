package com.lti.dto;

import java.time.LocalDate;

public class SearchFlightDto {
	
	String fromCity;
	String toCity;
	LocalDate dateT;
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
	public LocalDate getDateT() {
		return dateT;
	}
	public void setDateT(LocalDate dateT) {
		this.dateT = dateT;
	}
	
	
}
