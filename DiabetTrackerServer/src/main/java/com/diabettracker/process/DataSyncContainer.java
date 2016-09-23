package com.diabettracker.process;

import java.io.Serializable;

import com.diabettracker.model.TimeSeries;

public class DataSyncContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String granularity;
	private TimeSeries lowActivityProfile;
	private TimeSeries normalActivityProfile;
	private TimeSeries highActivityProfile;
	private TimeSeries distance;
	private TimeSeries footsteps;

	public DataSyncContainer(String granularity, TimeSeries lowActivityProfile, TimeSeries normalActivityProfile,
			TimeSeries highActivityProfile, TimeSeries distance, TimeSeries footsteps) {
		super();
		this.granularity = granularity;
		this.lowActivityProfile = lowActivityProfile;
		this.normalActivityProfile = normalActivityProfile;
		this.highActivityProfile = highActivityProfile;
		this.distance = distance;
		this.footsteps = footsteps;
	}

	public DataSyncContainer() {
		super();
	}

	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}

	public TimeSeries getLowActivityProfile() {
		return lowActivityProfile;
	}

	public void setLowActivityProfile(TimeSeries lowActivityProfile) {
		this.lowActivityProfile = lowActivityProfile;
	}

	public TimeSeries getNormalActivityProfile() {
		return normalActivityProfile;
	}

	public void setNormalActivityProfile(TimeSeries normalActivityProfile) {
		this.normalActivityProfile = normalActivityProfile;
	}

	public TimeSeries getHighActivityProfile() {
		return highActivityProfile;
	}

	public void setHighActivityProfile(TimeSeries highActivityProfile) {
		this.highActivityProfile = highActivityProfile;
	}

	public TimeSeries getDistance() {
		return distance;
	}

	public void setDistance(TimeSeries distance) {
		this.distance = distance;
	}

	public TimeSeries getFootsteps() {
		return footsteps;
	}

	public void setFootsteps(TimeSeries footsteps) {
		this.footsteps = footsteps;
	}

}
