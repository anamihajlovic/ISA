package com.isa.bartender;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.work.shift.WorkShift;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {
	
	private HttpSession httpSession;
	private final BartenderService bartenderService;
	

	@Autowired
	public BartenderController( HttpSession httpSession, final BartenderService bartenderService) {
		this.httpSession = httpSession;
		this.bartenderService = bartenderService;
		
	}
	
	
	@GetMapping("/getBartender")
	public Bartender getBartender() {		
		Bartender bartender = (Bartender) httpSession.getAttribute("user");
		return bartender;
	}
	
	@PutMapping(path = "/updateInfo")
	public Bartender updateBartender(@RequestBody Bartender bartender) {				
		
		try{
			bartenderService.save(bartender);
			httpSession.setAttribute("user", bartender);
		} catch(Exception e) {
			System.out.println("Greska pri update-u sankera");
			return null;
		}
		
		return bartender;	
	}
	
	@PutMapping(path = "/changePassword")
	public Bartender changeBartenderPassword(@RequestBody Bartender bartender) {
		
		if(bartender.getFirstLogIn())
			bartender.setFirstLogIn(false);
		
		try{
			bartenderService.save(bartender);
			httpSession.setAttribute("user", bartender);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre sankera");
			return null;
		}
		
		return bartender;	
	}
	
	@GetMapping("/readWorkSchedule/{id}")
	public WorkShift readBartenderWorkSchedule(@PathVariable Long bartenderId) {
		
		
		
		return null;
	}
	
	

	
}
