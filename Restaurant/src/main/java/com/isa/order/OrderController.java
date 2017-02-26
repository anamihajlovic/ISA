package com.isa.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.cook.Cook;
import com.isa.dish.Dish;
import com.isa.dish.DishService;
import com.isa.drink.Drink;
import com.isa.ordered.dish.DishStatus;
import com.isa.ordered.dish.OrderedDish;
import com.isa.ordered.dish.OrderedDishService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private HttpSession httpSession;
	private final OrderService orderService;
	private final OrderedDishService orderedDishService;
	private final DishService dishService;
	
	@Autowired
	public OrderController(HttpSession httpSession,final  OrderService orderService, final OrderedDishService orderedDishService, final DishService dishService) {		
		this.httpSession = httpSession;
		this.orderService = orderService;
		this.orderedDishService = orderedDishService;
		this.dishService = dishService;
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
			if(order.getRestaurantId().equals(restaurantId) && order.getOrderedDish().size() > 0) {
				
				for(OrderedDish orderedDish : order.getOrderedDish()) {
					Dish dish = dishService.findOne(orderedDish.getDishId());
					
					order.getDishStatusMap().put(dish.getId(), orderedDish.getStatus());
					
					if(!order.getDishes().contains(dish)) {
						order.getDishes().add(dish);
						order.getDishQuantity().put(dish.getId(), new Integer(1));
					
					} else {
						int value = order.getDishQuantity().get(dish.getId());						
						value++;
						order.getDishQuantity().put(dish.getId(), new Integer(value));												
					}										
				}				
				restaurantDishOrders.add(order);				
			}
						
		return restaurantDishOrders;		
	}
	
	
	
	@GetMapping(path = "/getPreparingDishes")
	public List<Order> getRestaurantPreparingDish() {
		
		List<Order> allOrders = orderService.findAll();	
		List<Order> preparingDishes = new ArrayList<Order>();
		
		Cook activeCook = (Cook) httpSession.getAttribute("user");
		
		for(Order order : allOrders) 
			if(order.getRestaurantId().equals(activeCook.getRestaurantId()) && order.getOrderedDish().size() > 0) {
				
				for(OrderedDish orderedDish : order.getOrderedDish()) {
					Dish dish = dishService.findOne(orderedDish.getDishId());

					order.getDishStatusMap().put(dish.getId(), orderedDish.getStatus());
					
					if(orderedDish.getStatus().equals(DishStatus.preparing) && orderedDish.getCookId().equals(activeCook.getId())) {						
						if(!order.getDishes().contains(dish)) {
							order.getDishes().add(dish);
							order.getDishQuantity().put(dish.getId(), new Integer(1));							
							
						} else {
							int value = order.getDishQuantity().get(dish.getId());						
							value++;
							order.getDishQuantity().put(dish.getId(), new Integer(value));												
						}	
												
					}			
				}
				
				if(order.getDishes().size() > 0)
					preparingDishes.add(order);
				
			}
		
		return preparingDishes;	
	}
	
	@GetMapping(path = "/getRestaurantDrinkOrders/{restaurantId}")
	public List<Order> getRestaurantDrinkOrders(@PathVariable Long restaurantId) {
		
		List<Order> allOrders = orderService.findAll();
		
		List<Order> restaurantDrinkOrders = new ArrayList<Order>();
		for(Order order : allOrders)
			if(order.getRestaurantId().equals(restaurantId) && order.getOrderedDrinks().size() > 0) {
				
				for(Drink drink : order.getOrderedDrinks()) {
					if(!order.getDrinks().contains(drink)) {
						order.getDrinks().add(drink);
						order.getDrinkQuantity().put(drink.getId(), new Integer(1));						
					
					} else {
						int value = order.getDrinkQuantity().get(drink.getId());
						value++;
						order.getDrinkQuantity().put(drink.getId(), new Integer(value));						
					}						
				}
				
				restaurantDrinkOrders.add(order);				
			}						
		
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
	
	@PutMapping(path = "/prepareDish/{orderId}")
	public String prepareDish(@PathVariable Long orderId, @RequestBody Dish dish) {
		
		List<OrderedDish> orderedDishes = orderedDishService.findByOrderIdAndDishId(orderId, dish.getId());
		Cook activeCook = (Cook)httpSession.getAttribute("user");
		
		for(OrderedDish orderedDish : orderedDishes) {
			orderedDish.setStatus(DishStatus.preparing);
			orderedDish.setCookId(activeCook.getId());
			
			try{
				orderedDishService.save(orderedDish);
			} catch(Exception e) {
				System.out.println(e);
				return "failure";
			}
		}					
		return "success";
	}
	
	@PutMapping(path = "/serveDish/{orderId}")
	public String serveDish(@PathVariable Long orderId, @RequestBody Dish dish) {
		
		List<OrderedDish> orderedDishes = orderedDishService.findByOrderIdAndDishId(orderId, dish.getId());
		
		for(OrderedDish orderedDish : orderedDishes) {
			orderedDish.setStatus(DishStatus.ready);			
			
			try{
				orderedDishService.save(orderedDish);
				
				Order order = orderService.findOne(orderId);
				dishOrderReady(order);
				
			} catch(Exception e) {
				System.out.println(e);
				return "failure";
			}
		}					
		return "success";
	}
	
	public void dishOrderReady(Order order) {
		
		boolean flag = true;
		for(OrderedDish dish : order.getOrderedDish())
			if(!dish.getStatus().equals(DishStatus.ready))
				flag = false;
		
		if(flag) {
			try{
				orderService.save(order);
			}catch(Exception e) {
				System.out.println(e);				
			}			
		}
	}
	
	
}
