package com.diabettracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calory")
public class Calory {

	@Id
	@GeneratedValue
	@Column(name = "idCalory")
	private int caloryId;

	@Column(name = "value")
	private int value;

	@Column(name = "hour")
	private Date hour;

	@Column(name = "userId")
	private String userId;

	@Column(name = "dayOfWeek")
	private String dayOfWeek;

	public Calory() {
		super();
	}

	public Calory(int caloryId, int value, Date hour, String userId, String dayOfWeek) {
		super();
		this.caloryId = caloryId;
		this.value = value;
		this.hour = hour;
		this.userId = userId;
		this.dayOfWeek = dayOfWeek;
	}

	public int getCaloryId() {
		return caloryId;
	}

	public void setCaloryId(int caloryId) {
		this.caloryId = caloryId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
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

}
