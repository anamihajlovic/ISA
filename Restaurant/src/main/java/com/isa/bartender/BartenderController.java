package com.isa.bartender;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {
	
	private HttpSession session;
	private final BartenderService bartenderService;

	@Autowired
	public BartenderController(final HttpSession httpSession, final BartenderService bartenderService) {
		this.session = httpSession;
		this.bartenderService = bartenderService;
	}
	
	
	@GetMapping("/getBartender")
	public Bartender getBartender() {		
		Bartender bartender = (Bartender) session.getAttribute("user");
		return bartender;
	}
	
	@PutMapping(path = "/{id}")
	public void updateBartender(@PathVariable Long id,@RequestBody Bartender bartender) {
		System.out.println(bartender.getBirthday());
		bartenderService.save(bartender);
	}
	
	

	
}
