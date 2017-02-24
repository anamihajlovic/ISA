package com.isa.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private HttpSession httpSession;
	private final OrderService orderService;
	
	@Autowired
	public OrderController(HttpSession httpSession,final  OrderService orderService) {		
		this.httpSession = httpSession;
		this.orderService = orderService;
	}
	
	@GetMapping(path = "/getOrder/{id}")
	public Order getOrder(@PathVariable Long id) {		
		Order order = (Order) orderService.findOne(id);
		return order;
	}
		
	@GetMapping(path = "/getRestaurantDishOrders/{restaurantId}")
	public List<Order> getRestaurantDishOrders(@PathVariable Long restaurantId) {
		List<Order> allOrders = orderService.findAll();
		
		List<Order> restaurantDishOrders = new ArrayList<Order>();
		for(Order order : allOrders)
			if(order.getRestaurantId() == restaurantId && order.getOrderedDish().size() > 0)
				restaurantDishOrders.add(order);
		
		return restaurantDishOrders;		
	}
	
	@GetMapping(path = "/getRestaurantDrinkOrders/{restaurantId}")
	public List<Order> getRestaurantDrinkOrders(@PathVariable Long restaurantId) {
		List<Order> allOrders = orderService.findAll();
		
		List<Order> restaurantDrinkOrders = new ArrayList<Order>();
		for(Order order : allOrders)
			if(order.getRestaurantId() == restaurantId && order.getOrderedDrinks().size() > 0)
				restaurantDrinkOrders.add(order);
		
		return restaurantDrinkOrders;	
	}
	
	
	@GetMapping(path = "/getAllRestaurantOrders/{restaurantId}")
	public List<Order> getAllOrders(@PathVariable Long restaurantId) {
		List<Order> allOrders = orderService.findAll();
		
		List<Order> restaurantOrders = new ArrayList<Order>();
		for(Order order : allOrders)
			if(order.getRestaurantId() == restaurantId)
				restaurantOrders.add(order);
		
		return restaurantOrders;
		
	}
	
	@PutMapping(path = "/prepareDrinks")
	public Order prepareDrinks(@RequestBody Order order) {			
		
		if(order != null) {
			order.setId(order.getId());
			order.setDrinksStatus(OrderItemStatus.prepared);			
			
			try{
				orderService.save(order);			
			} catch(Exception e) {
				System.out.println(e);
				System.out.println("Greska pri update-u porudzbine");
				return null;
			}						
		}
		
		return order;
		
	}

}
