package com.isa.bartender;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {
	
	private HttpSession httpSession;
	private final BartenderService bartenderService;

	@Autowired
	public BartenderController(final HttpSession httpSession, final BartenderService bartenderService) {
		this.httpSession = httpSession;
		this.bartenderService = bartenderService;
	}
}
