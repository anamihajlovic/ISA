package com.isa.cook;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping(path = "/updateInfo")
	public Cook updateCook(@RequestBody Cook cook) {		
		
		try{
			cookService.save(cook);
		} catch(Exception e) {
			System.out.println("Greska pri update-u kuvara.");
			return null;
		}
		
		return cook;				
	}
	
	@PutMapping(path = "/changePassword")
	public Cook changeCookPassword(@RequestBody Cook cook) {
		
		try{
			cookService.save(cook);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre kuvara");
			return null;
		}
		
		return cook;	
	}

}
