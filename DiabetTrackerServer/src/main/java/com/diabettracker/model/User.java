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

	@Column(name = "accessToken")
	private String accessToken;

	@Column(name = "refreshToken")
	private String refreshToken;

	public User() {
		super();
	}

	public User(String fitbitUserId, String accessToken, String refreshToken) {
		super();
		this.fitbitUserId = fitbitUserId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

}
