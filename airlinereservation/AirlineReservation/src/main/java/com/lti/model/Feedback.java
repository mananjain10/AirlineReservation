package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_feedback")
public class Feedback {
	
	@Id
	@SequenceGenerator(name="feedback_seq",initialValue=80001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="feedback_seq")
	int feedbackId;
	
	String name;
	String email;
	String reference;
	Boolean subscription;
	String msg;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Boolean getSubscription() {
		return subscription;
	}
	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}
