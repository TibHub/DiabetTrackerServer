package com.diabettracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private int userId;

	@Column(name = "fitbitUserId")
	private String fitbitUserId;

	@Column(name = "accessToken", length = 1000)
	private String accessToken;

	@Column(name = "refreshToken", length = 500)
	private String refreshToken;

	@Column(name = "lockedForUpdate")
	private boolean lockedForUpdate;

	@Column(name = "lastUpdate")
	private String lastUpdate;

	public User() {
		super();
		this.lockedForUpdate = false;
		this.lastUpdate = "00:01";
	}

	public User(String fitbitUserId, String accessToken, String refreshToken) {
		super();
		this.fitbitUserId = fitbitUserId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.lockedForUpdate = false;
		this.lastUpdate = "00:01";
	}

	public String getFitbitUserId() {
		return fitbitUserId;
	}

	public void setFitbitUserId(String fitbitUserId) {
		this.fitbitUserId = fitbitUserId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public boolean isLockedForUpdate() {
		return lockedForUpdate;
	}

	public void setLockedForUpdate(boolean lockedForUpdate) {
		this.lockedForUpdate = lockedForUpdate;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
