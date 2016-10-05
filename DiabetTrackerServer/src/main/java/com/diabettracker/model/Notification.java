package com.diabettracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idNotification")
	private int notificationId;

	@Column(name = "hour")
	private String hour;

	@Column(name = "date")
	private String date;

	@Column(name = "message")
	private String message;

	// /** Pour remonter les valeurs paramétrées par l'utilisateur et
	// susceptibles de modifier l'interprétation qui
	// * doit etre faite de la notification **/
	// @Column(name = "info")
	// private List<TimeSeries> info;

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
