package com.diabettracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.model.Calory;

@Controller
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private ICaloryDAO dao;

	public List<Calory> doGet() {
		List<Calory> calories = dao.getAllSamples();
		return calories;
	}

	public String doPostAuthCode(String code) {
		return "Le code re�u est " + code;
	}

}
