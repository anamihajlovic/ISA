package com.isa.ordered.dish;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderedDish")
public class OrderedDishController {
	
	private HttpSession httpSession;
	private final OrderedDishService orderedDishService;
	
	@Autowired
	public OrderedDishController(HttpSession httpSession, final OrderedDishService orderedDishService) {
		super();
		this.httpSession = httpSession;
		this.orderedDishService = orderedDishService;
	}
	
}
