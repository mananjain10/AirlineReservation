package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

	@Entity
	@Table(name="tbl_contactus")
	public class ContactUs {
		
		@Id
		@SequenceGenerator(name="contactus_seq",initialValue=90001,allocationSize=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="contactus_seq")
		int contactusId;
		
		String firstName;
		String lastName;
		String email;
		String phone;
		String msg;
		
		public int getcontactusId() {
			return contactusId;
		}
		public void setcontactusId(int contactusId) {
			this.contactusId = contactusId;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		
		
	
}
