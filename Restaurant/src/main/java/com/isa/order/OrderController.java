package com.isa.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.isa.cook.Cook;
import com.isa.dish.Dish;
import com.isa.dish.DishService;
import com.isa.drink.Drink;
import com.isa.drink.DrinkService;
import com.isa.ordered.dish.DishStatus;
import com.isa.ordered.dish.OrderedDish;
import com.isa.ordered.dish.OrderedDishService;
import com.isa.reservation.Reservation;
import com.isa.reservation.ReservationService;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.waiter.Waiter;
import com.isa.waiter.WaiterService;
import com.isa.work.day.WorkDay;
import com.isa.work.shift.WorkShift;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private HttpSession httpSession;
	private final OrderService orderService;
	private final OrderedDishService orderedDishService;
	private final DishService dishService;
	private final RestaurantService restaurantService;
	private final WaiterService waiterService;
	private final ReservationService reservationService;
	private final DrinkService drinkService;
	
	@Autowired		
	public OrderController(HttpSession httpSession, OrderService orderService, OrderedDishService orderedDishService,
			DishService dishService, RestaurantService restaurantService, WaiterService waiterService,
			ReservationService reservationService, DrinkService drinkService) {
		super();
		this.httpSession = httpSession;
		this.orderService = orderService;
		this.orderedDishService = orderedDishService;
		this.dishService = dishService;
		this.restaurantService = restaurantService;
		this.waiterService = waiterService;
		this.reservationService = reservationService;
		this.drinkService = drinkService;
	}

	@GetMapping(path = "/getOrder/{id}")
	public Order getOrder(@PathVariable Long id) {		
		Order order = (Order) orderService.findOne(id);
		return order;
	}
	
	@PostMapping(path = "/addOrder/{reservationId}", consumes="application/json; charset=utf8")
	public Order addOrder(@PathVariable Long reservationId, @RequestBody String items) {
		String dishes = items.split("-")[0];
		String drinks = items.split("-")[1];

		System.out.println("Pogodjena metoda addOrder " + reservationId + " dishes " + dishes + " drinks " + drinks);
		Reservation reservation = reservationService.findById(reservationId);
		
		Order order = new Order();
		order.setRestaurantId(reservation.getResId());
		order.setOrderDate(reservation.getDate());
		order.setOrderStatus(OrderStatus.ordered);
		order.setDrinksStatus(OrderItemStatus.ordered);
		
		try {
			orderService.save(order);
		} catch (Exception e) {
			System.out.println("Neuspesno cuvanje porudzbine-prvo cuvanje");
			return null;
		}
		
		if(!dishes.equals("")) {
			Integer[] dishesId = makeIntegerArray(dishes);
			ArrayList<OrderedDish> ordered = new ArrayList<OrderedDish>();
			for(Integer id: dishesId) {
				OrderedDish od = new OrderedDish();
				od.setOrderId(order.getId());
				od.setDishId(id);
				od.setStatus(DishStatus.ordered);
				ordered.add(od);
			}
			order.setOrderedDish(ordered);
		}
		
		if (!drinks.equals("")) {
			Integer[] drinksId = makeIntegerArray(drinks);
			ArrayList<Drink> ordered = new ArrayList<Drink>();

			for(Integer id: drinksId) {
				Drink drink = drinkService.findOne(id);
				ordered.add(drink);
			}
			order.setOrderedDrinks(ordered);
		}
		
		try {
			orderService.save(order);
		} catch (Exception e) {
			System.out.println("Neuspesno cuvanje porudzbine-drugo cuvanje");
			orderService.delete(order.getId());
			return null;
		}
		
		reservation.setOrders(new ArrayList<Order>());
		reservation.getOrders().add(order);
		
		try {
			reservationService.save(reservation);
		} catch(Exception e) {
			System.out.println("Neuspeno dodavanje objekta Order u odgovarajucu rezervaciju.");
			orderService.delete(order.getId());
			return null;
		}
		
		return order;
		
	}
	
	private Integer[] makeIntegerArray(String string) {
		String[] array = string.split(";");
		
		Integer[] integerArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
		  integerArray[i] = Integer.valueOf(array[i]);
		}
		
		return integerArray;
	}

	@PutMapping(path = "/acceptOrder/{id}")
	public Order acceptOrder(@PathVariable Long id) {
		
		try{
			Waiter activeWaiter = (Waiter) httpSession.getAttribute("user");
			Order order = (Order) orderService.findOne(id);
			order.setOrderStatus(OrderStatus.accepted);
						
			Date now = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
			String currentTime = timeFormatter.format(now);						
			order.setAcceptanceTime(currentTime);						
			
			
			order.setWaiterId(activeWaiter.getId());
			orderService.save(order);
			return order;
		}catch(Exception e) {
			return null;
		}			
	}
	
	@PutMapping(path = "/finishOrder/{id}")
	public String finishOrder(@PathVariable Long id) {
	
		try{				
			Order order = (Order) orderService.findOne(id);
			order.setOrderStatus(OrderStatus.paid);										
			orderService.save(order);
			return "success";
		}catch(Exception e) {
			return "failure";
		}	
	}
	
	
	@GetMapping(path = "/compareWaiters/{orderId}")
	public Waiter compareWaiters(@PathVariable Long orderId) {
						
		try{
			Date today = new Date();			
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String format = formatter.format(today);						
			
			Order order = orderService.findOne(orderId);
			Waiter firstWaiter = waiterService.findOne(order.getWaiterId());
			Waiter currentWaiter = (Waiter) httpSession.getAttribute("user");
			WorkShift waiterShift = null;
			
			//pronadji smenu konobara
			Restaurant restaurant = restaurantService.findOne(firstWaiter.getRestaurantId());
			for(WorkDay day : restaurant.getWorkDays()) {								
				String workDate = formatter.format(day.getDay());
				if(workDate.equals(format)) {
								
					for(WorkShift shift : day.getWorkShifts())
						if(shift.getWaiters().indexOf(firstWaiter) == -1) {
							waiterShift = shift;
							break;
						}
				}
																		
			}
			
			if(waiterShift != null) {
				String shiftEndString = waiterShift.getEndTime();
				String acceptanceTime = order.getAcceptanceTime();				
				
				SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:mm:ss");
				String currentTime = timeFormatter.format(today);			
				
				Date acceptance = timeFormatter.parse(acceptanceTime);
				Date shiftEnd = timeFormatter.parse(shiftEndString);
				Date current = timeFormatter.parse(currentTime);
				

		        long firstWaiterDuration = shiftEnd.getTime() - acceptance.getTime();
				long secondWaiterDuration = current.getTime() - shiftEnd.getTime();
				
				if(firstWaiterDuration >= secondWaiterDuration) {
					order.setWaiterId(firstWaiter.getId());
					orderService.save(order);
					return firstWaiter;
				}
					
				else {
					order.setWaiterId(currentWaiter.getId());
					orderService.save(order);
					return currentWaiter;
				}
			}
			return null;					
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
		
	}
		
	@GetMapping(path = "/getRestaurantDishOrders/{restaurantId}")
	public List<Order> getRestaurantDishOrders(@PathVariable Long restaurantId) {		
		
		List<Order> allOrders = orderService.findAll();	
		List<Order> restaurantDishOrders = new ArrayList<Order>();
		
		for(Order order : allOrders) 
			if(order.getRestaurantId().equals(restaurantId) && order.getOrderStatus().equals(OrderStatus.accepted) && order.getOrderedDish().size() > 0) {
				
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
			if(order.getRestaurantId().equals(restaurantId) && (order.getOrderStatus().equals(OrderStatus.accepted) || order.getOrderStatus().equals(OrderStatus.ready))  && order.getOrderedDrinks().size() > 0) {
				
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
		List<Order> restaurantOrders = new ArrayList<Order>();
		
		try{
			List<Order> allOrders = orderService.findAll();			
			
			for(Order order : allOrders)
				if(order.getRestaurantId().equals(restaurantId) && !order.getOrderStatus().equals(OrderStatus.paid)) {
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
					for(OrderedDish orderedDish : order.getOrderedDish()) {
						Dish dish = dishService.findOne(orderedDish.getDishId());
						if(!order.getDishes().contains(dish)) {
							order.getDishes().add(dish);
							order.getDishQuantity().put(dish.getId(), new Integer(1));							
							
						} else {
							int value = order.getDishQuantity().get(dish.getId());						
							value++;
							order.getDishQuantity().put(dish.getId(), new Integer(value));												
						}	
					}
					restaurantOrders.add(order);
				}
					
				
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
							
		return restaurantOrders;		
	}
	
	@GetMapping(path = "/getServedRestaurantOrders/{restaurantId}")
	public List<Order> getServedOrders(@PathVariable Long restaurantId) {
		List<Order> restaurantOrders = new ArrayList<Order>();
		
		try{
			List<Order> allOrders = orderService.findAll();			
			
			for(Order order : allOrders)
				if(order.getRestaurantId().equals(restaurantId) && order.getOrderStatus().equals(OrderStatus.served)) {
					
					for(Drink drink : order.getOrderedDrinks()) 
						if(!order.getDrinks().contains(drink)) {
							order.getDrinks().add(drink);
							order.getDrinkQuantity().put(drink.getId(), new Integer(1));						
						
						} else {
							int value = order.getDrinkQuantity().get(drink.getId());
							value++;
							order.getDrinkQuantity().put(drink.getId(), new Integer(value));						
						}	
					
					for(OrderedDish orderedDish : order.getOrderedDish()) {
						Dish dish = dishService.findOne(orderedDish.getDishId());
						if(!order.getDishes().contains(dish)) {
							order.getDishes().add(dish);
							order.getDishQuantity().put(dish.getId(), new Integer(1));							
							
						} else {
							int value = order.getDishQuantity().get(dish.getId());						
							value++;
							order.getDishQuantity().put(dish.getId(), new Integer(value));												
						}	
					}
					restaurantOrders.add(order);								
				}
					
		
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}							
		return restaurantOrders;		
	}
	
	@GetMapping(path = "/getPaidRestaurantOrders/{restaurantId}")
	public List<Order> getPaidOrders(@PathVariable Long restaurantId) {
		List<Order> restaurantOrders = new ArrayList<Order>();
		
		try{
			List<Order> allOrders = orderService.findAll();			
			
			for(Order order : allOrders)
				if(order.getRestaurantId().equals(restaurantId) && order.getOrderStatus().equals(OrderStatus.paid)) {										
					restaurantOrders.add(order);								
				}	
			
			return restaurantOrders;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}							
				
	}
	
	
	
	@PutMapping(path = "/prepareDrinks")
	public Order prepareDrinks(@RequestBody Order order) {			
		
		if(order != null) {			
			order.setDrinksStatus(OrderItemStatus.prepared);			
			
			try{
				orderService.save(order);	
				orderReady(order);
			} catch(Exception e) {
				System.out.println(e);
				System.out.println("Greska pri update-u porudzbine");
				return null;
			}						
		}
		
		return order;		
	}
	
	@PutMapping(path = "/serveOrder")
	public String serveOrder(@RequestBody Order order) {
		
		if(order != null) {
			order.setOrderStatus(OrderStatus.served);
		
			try{
				orderService.save(order);
			}catch(Exception e) {
				System.out.println(e);
				return "failure";
			}
		}
		
		return "success";
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
				orderReady(order);
				
			} catch(Exception e) {
				System.out.println(e);
				return "failure";
			}
		}					
		return "success";
	}
	
	@PostMapping(path = "/removeDrink/{drinkId}")
	public String removeDrink(@PathVariable Integer drinkId, @RequestBody Order order) {
		
		return "success";
	}
	
	public void dishOrderReady(Order order) {		
		boolean flag = true;
		for(OrderedDish dish : order.getOrderedDish())
			if(!dish.getStatus().equals(DishStatus.ready))
				flag = false;
		
		if(flag) {
			try{
				order.setDishStatus(OrderItemStatus.prepared);
				orderService.save(order);
			}catch(Exception e) {
				System.out.println(e);				
			}			
		}
	}
	
	
	public void orderReady(Order order) {
		
		if((order.getOrderedDish().size() == 0 && order.getDrinksStatus().equals(OrderItemStatus.prepared)) ||
		   (order.getOrderedDrinks().size() == 0 && order.getDishStatus().equals(OrderItemStatus.prepared))	||
		   (order.getOrderedDish().size() > 0 && order.getOrderedDrinks().size() > 0 && order.getDrinksStatus().equals(OrderItemStatus.prepared) && order.getDishStatus().equals(OrderItemStatus.prepared)))    {
					   
			order.setOrderStatus(OrderStatus.ready);
			
			try{
				orderService.save(order);
			} catch(Exception e) {
				System.out.println(e);
			}
		
		}																	
	}
	
	
}
