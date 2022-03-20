package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_admin1")
public class Admin {

	@Id
	@SequenceGenerator(name="admin_seq",initialValue=1001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="admin_seq")
	int admin_id;
	
	String aUserName;
	String aPassword;
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getaUserName() {
		return aUserName;
	}
	public void setaUserName(String aUserName) {
		this.aUserName = aUserName;
	}
	public String getaPassword() {
		return aPassword;
	}
	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}
	
}
