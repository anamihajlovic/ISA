package com.isa.cook;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cooks")
public class CookController {
	
	private HttpSession httpSession;
	private final CookService cookService;
	
	@Autowired
	public CookController(final HttpSession httpSession, final CookService cookService) {
		this.httpSession = httpSession;
		this.cookService = cookService;
	}

}
