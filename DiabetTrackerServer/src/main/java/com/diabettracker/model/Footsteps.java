package com.diabettracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "footsteps")
public class Footsteps implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idFootsteps")
	private int footstepsId;

	@Column(name = "value")
	private Double value;

	@Column(name = "hour")
	private String hour;

	@Column(name = "userId")
	private String userId;

	@Column(name = "dayOfWeek")
	private String dayOfWeek;

	@Column(name = "date")
	private String date;

	public Footsteps() {
		super();
	}

	public Footsteps(Double value, String hour, String userId, String dayOfWeek, String date) {
		super();
		this.value = value;
		this.hour = hour;
		this.userId = userId;
		this.dayOfWeek = dayOfWeek;
		this.date = date;
	}

	public int getFootstepsId() {
		return footstepsId;
	}

	public void setFootstepsId(int footstepsId) {
		this.footstepsId = footstepsId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Heure : " + getHour() + " Valeur : " + getValue() + " Jour : " + getDayOfWeek() + " Date : "
				+ getDate();
	}

}
