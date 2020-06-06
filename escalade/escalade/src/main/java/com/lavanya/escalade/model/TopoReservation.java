package com.lavanya.escalade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "topo_reservation")
public class TopoReservation {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name = "id")
	int reservationId;
	
	String message;
	
	@Column (name = "date_of_request")
	Date requestDate;
	
	public TopoReservation() {
		
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


}