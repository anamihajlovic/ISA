package com.isa.bidder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.restaurant.RestaurantService;


@RestController
@RequestMapping("/bidder")
public class BidderController {
	
	private final BidderService bidderService;
	private final RestaurantService restaurantService;
	private HttpSession httpSession;

	@Autowired
	public BidderController(final HttpSession httpSession, final BidderService bidderService,
			final RestaurantService restaurantService) {
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.httpSession = httpSession;
	}


}
