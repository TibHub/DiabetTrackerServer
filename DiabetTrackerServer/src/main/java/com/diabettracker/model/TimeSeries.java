package com.diabettracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timeSeries")
	private List<HourValuePair> hourValuePairs;

	public TimeSeries() {
		super();
		this.type = "";
		this.granularity = "";
		this.hourValuePairs = new ArrayList<HourValuePair>();
	}

	public TimeSeries(String type, String granularity) {
		super();
		this.type = type;
		this.granularity = granularity;
		this.hourValuePairs = new ArrayList<HourValuePair>();
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

}
