package com.lti.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_passenger")
public class Passenger {
	
	@Id
	@SequenceGenerator(name="pass_seq",initialValue=50001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pass_seq")
	int passenger_id;
	String firstName;
	String lastName;
	int age;
	String gender;
	String adultOrChild;
	
	@OneToOne(mappedBy = "passenger",cascade = CascadeType.ALL)
	Ticket ticket;
	
	public int getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAdultOrChild() {
		return adultOrChild;
	}
	public void setAdultOrChild(String adultOrChild) {
		this.adultOrChild = adultOrChild;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
	

}
