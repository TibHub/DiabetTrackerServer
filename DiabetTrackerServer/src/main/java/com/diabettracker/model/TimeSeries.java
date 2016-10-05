package com.diabettracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "time_series")
public class TimeSeries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "granularity")
	private String granularity;

	@Column(name = "dayOfWeek")
	private String dayOfWeek;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timeSeries", cascade = { CascadeType.ALL })
	private List<HourValuePair> hourValuePairs;

	public TimeSeries() {
		super();
		this.type = "";
		this.granularity = "";
		this.hourValuePairs = new ArrayList<HourValuePair>();
		this.dayOfWeek = "";
	}

	public TimeSeries(String type, String granularity, String dayOfWeek) {
		super();
		this.type = type;
		this.granularity = granularity;
		this.hourValuePairs = new ArrayList<HourValuePair>();
		this.dayOfWeek = dayOfWeek;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}

	@JsonManagedReference
	public List<HourValuePair> getHourValuePairs() {
		return hourValuePairs;
	}

	public void setHourValuePairs(List<HourValuePair> hourValuePairs) {
		this.hourValuePairs = hourValuePairs;
	}

	@Transient
	public void addHourValuePair(HourValuePair pair) {
		this.hourValuePairs.add(pair);
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}
