package com.isa.bidder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.Bartender;
import com.isa.res.manager.RestaurantManager;
import com.isa.restaurant.RestaurantService;


@RestController
@RequestMapping("/bidder")
public class BidderController {
	
	private final BidderService bidderService;
	private final RestaurantService restaurantService;
	private HttpSession httpSession;
	private Bidder bidder ;

	@Autowired
	public BidderController(final HttpSession httpSession, final BidderService bidderService,
			final RestaurantService restaurantService) {
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.httpSession = httpSession;
	}

	@GetMapping("/checkRights")
	public Bidder checkRights() {		
		bidder =  (Bidder) httpSession.getAttribute("user");
		return bidder;
			
	}
	

	@PutMapping(path = "/{id}")
	public Bidder updateResManager(@PathVariable Long id,@RequestBody Bidder bidd) {
		bidder =  (Bidder) httpSession.getAttribute("user");
		bidderService.findOne(id);		
		bidd.setId(id);
		return bidderService.save(bidd);
	}	
	
	@PutMapping(path = "/firstLogIn")
	public Bidder changeBidderPassword(@RequestBody Bidder bidder) {
		
		try{
			bidder.setFirstLogIn(false);
			bidderService.save(bidder);
			httpSession.setAttribute("user", bidder);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre sankera");
			return null;
		}
		
		return bidder;	
	}
	


}
