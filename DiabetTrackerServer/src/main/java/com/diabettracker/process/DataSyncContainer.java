package com.diabettracker.process;

import java.io.Serializable;
import java.util.List;

import com.diabettracker.model.Notification;
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
	private TimeSeries burntCals;
	private List<Notification> notifications;
	private double burntCaloriesVal;
	private double distanceVal;
	private double foostepsVal;

	public DataSyncContainer(String granularity, TimeSeries lowActivityProfile, TimeSeries normalActivityProfile,
			TimeSeries highActivityProfile, TimeSeries distance, TimeSeries footsteps, TimeSeries burntCals,
			List<Notification> notifications, double burntCaloriesVal, double distanceVal, double foostepsVal) {
		super();
		this.granularity = granularity;
		this.lowActivityProfile = lowActivityProfile;
		this.normalActivityProfile = normalActivityProfile;
		this.highActivityProfile = highActivityProfile;
		this.distance = distance;
		this.footsteps = footsteps;
		this.burntCals = burntCals;
		this.notifications = notifications;
		this.burntCaloriesVal = burntCaloriesVal;
		this.distanceVal = distanceVal;
		this.foostepsVal = foostepsVal;
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

	public TimeSeries getBurntCals() {
		return burntCals;
	}

	public void setBurntCals(TimeSeries burntCals) {
		this.burntCals = burntCals;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public double getBurntCaloriesVal() {
		return burntCaloriesVal;
	}

	public void setBurntCaloriesVal(double burntCaloriesVal) {
		this.burntCaloriesVal = burntCaloriesVal;
	}

	public double getDistanceVal() {
		return distanceVal;
	}

	public void setDistanceVal(double distanceVal) {
		this.distanceVal = distanceVal;
	}

	public double getFoostepsVal() {
		return foostepsVal;
	}

	public void setFoostepsVal(double foostepsVal) {
		this.foostepsVal = foostepsVal;
	}

}
