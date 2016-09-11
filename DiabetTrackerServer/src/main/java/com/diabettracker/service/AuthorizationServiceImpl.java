package com.diabettracker.service;

import org.springframework.stereotype.Controller;

import com.sun.ws.rest.api.representation.FormURLEncodedProperties;

@Controller
public class AuthorizationServiceImpl implements IAuthorizationService {

	public String doPostAuthCode(FormURLEncodedProperties p) {
		String code = p.get("code");
		return "Le code reçu est " + code;
	}

	public String doGet() {
		return "coucou";
	}

}
