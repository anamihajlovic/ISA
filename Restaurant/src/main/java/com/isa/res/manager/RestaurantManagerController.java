package com.isa.res.manager;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.Bartender;
import com.isa.bartender.BartenderService;
import com.isa.bidder.*;
import com.isa.cook.Cook;
import com.isa.cook.CookService;
import com.isa.dish.*;
import com.isa.dish.DishService;
import com.isa.drink.*;
import com.isa.drink.DrinkService;
import com.isa.foodstuf.Foodstuff;
import com.isa.foodstuf.FoodstuffService;
import com.isa.restaurant.*;
import com.isa.user.Role;
import com.isa.waiter.Waiter;
import com.isa.waiter.WaiterService;


@RestController
@RequestMapping("/resManager")
public class RestaurantManagerController {
	
	private HttpSession httpSession;
	private final RestaurantService restaurantService;
	private final RestaurantManagerService restaurantManagerService;
	private final WaiterService waiterService;
	private final CookService cookService;
	private final BartenderService bartenderService;
	private final BidderService bidderService;
	private final FoodstuffService foodstuffService;
	private final DrinkService drinkService;
	private final DishService dishService;
	private RestaurantManager restaurantManager;
	
	@Autowired
	public RestaurantManagerController(HttpSession httpSession, RestaurantService restaurantService,
			RestaurantManagerService restaurantManagerService, WaiterService waiterService, CookService cookService,
			BartenderService bartenderService, BidderService bidderService, FoodstuffService foodstuffService,
			DrinkService drinkService, DishService dishService) {
		super();
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.restaurantManagerService = restaurantManagerService;
		this.waiterService = waiterService;
		this.cookService = cookService;
		this.bartenderService = bartenderService;
		this.bidderService = bidderService;
		this.foodstuffService = foodstuffService;
		this.drinkService = drinkService;
		this.dishService = dishService;
	}
		

	@GetMapping("/checkRights")
	public RestaurantManager checkRights() {		
			 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		return restaurantManager;
			
	}
	
	@PostMapping(path = "/newWaiter")
	public String saveEmployee(@RequestBody Waiter emp) {
		//System.out.println("uslo");
		if (emp != null){
			emp.setFirstLogIn(true);
			waiterService.save(emp);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());	
			emp.setRestaurant(r);
			r.getWaiters().add(emp);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);	
		return "dodato";
	}
		else
			return "nije";		
	}
	
	@PostMapping(path = "/newCook")
	public Cook saveEmployee(@RequestBody Cook emp) {
		//System.out.println("uslo");
		if (emp != null){
			emp.setFirstLogIn(true);
			cookService.save(emp);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			emp.setRestaurant(r);
			r.getCooks().add(emp);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);
		return emp;
	}
		else
			return null;		
	}
	
	@PostMapping(path = "/newBartender")
	public Bartender saveEmployee(@RequestBody Bartender emp) {
		//System.out.println("uslo");
		if (emp != null){
			emp.setFirstLogIn(true);
			bartenderService.save(emp);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			emp.setRestaurant(r);
			r.getBartenders().add(emp);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);
		return emp;
	}
		else
			return null;		
	}

	@PostMapping(path = "/newBidder")
	public Bidder saveBidder(@RequestBody Bidder bidder) {
		//System.out.println("uslo");
		if (bidder != null){
			bidder.setFirstLogIn(true);
			bidder.setUserRole(Role.bidder);
			bidderService.save(bidder);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			r.getBidders().add(bidder);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);
		return bidder;
	}
		else
			return null;		
	}

	@PostMapping(path = "/newFoodstuff")
	public String saveFoodstuff(@RequestBody Foodstuff food) {
		//System.out.println("uslo");
		if (food != null){
			foodstuffService.save(food);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			r.getFoodstuffs().add(food);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);	
		return "dodato";
	}
		else
			return "nije";		
	}
	@PostMapping(path = "/newDish")
	public String saveDish(@RequestBody Dish dish) {
		//System.out.println("uslo");
		if (dish != null){
			dishService.save(dish);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			r.getDishes().add(dish);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);	
		return "dodato";
	}
		else
			return "nije";		
	}
	@PostMapping(path = "/newDrink")
	public String saveDrink(@RequestBody Drink drink) {
		//System.out.println("uslo");
		if (drink != null){
			drinkService.save(drink);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			r.getDrinks().add(drink);
			r.setId(restaurantManager.getIdRestaurant());
			restaurantService.save(r);	
		return "dodato";
	}
		else
			return "nije";		
	}
	@GetMapping(path = "/restaurant")
	public Restaurant getRestaurant() {
		try {
			Restaurant r  =restaurantService.findOne(restaurantManager.getIdRestaurant());
			  return r;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
  
	}
	@GetMapping(path = "/dishes")
	public List<Dish> findAllDishes() {
			List<Dish> dishes =restaurantService.findOne(restaurantManager.getIdRestaurant()).getDishes();
		  return dishes;
  
	}
	@GetMapping(path = "/drinks")
	public List<Drink> findAllDrinks() {
	    List<Drink> drinks =restaurantService.findOne(restaurantManager.getIdRestaurant()).getDrinks();
		return drinks;
	}
	@GetMapping(path = "/foodstuffs")
	public List<Foodstuff> findAllFoodstuffs() {
	    List<Foodstuff> foodstuffs =restaurantService.findOne(restaurantManager.getIdRestaurant()).getFoodstuffs();
		return foodstuffs;
	}
	
	
	@GetMapping(path = "/waiters")
	public List<Waiter> findAllWaiters() {
	    List<Waiter> waiters =restaurantService.findOne(restaurantManager.getIdRestaurant()).getWaiters();
		return waiters;
	}
	@GetMapping(path = "/cooks")
	public List<Cook> findAllCooks() {
	    List<Cook> cooks =restaurantService.findOne(restaurantManager.getIdRestaurant()).getCooks();
		return cooks;
	}
	@GetMapping(path = "/bartenders")
	public List<Bartender> findAllBartenders() {
	    List<Bartender> bartenders =restaurantService.findOne(restaurantManager.getIdRestaurant()).getBartenders();
		return bartenders;
	}
	@GetMapping(path = "/bidders")
	public List<Bidder> findAllBidders() {
	    List<Bidder> bidders =restaurantService.findOne(restaurantManager.getIdRestaurant()).getBidders();
		return bidders;
	}
	
	@GetMapping(path = "/foodstuff/{id}")
	public Foodstuff findFoodstuff(@PathVariable Integer id) {
		Foodstuff foodstuff =foodstuffService.findOne(id);
		return foodstuff;
	}
	@GetMapping(path = "/dish/{id}")
	public Dish findDish(@PathVariable Integer id) {
		Dish dish =dishService.findOne(id);
		return dish;
	}
	@GetMapping(path = "/drink/{id}")
	public Drink findDrink(@PathVariable Integer id) {
		Drink drink =drinkService.findOne(id);
		return drink;
	}
	
	@DeleteMapping(path = "/deleteFoodstuff/{id}")
	public String deleteFoodstuff(@PathVariable Integer id) {
		if(id!=null){
			Foodstuff f = foodstuffService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getFoodstuffs().remove(f);
			foodstuffService.delete(id);
		return "yes";
			}else return "no";	
	}
	@DeleteMapping(path = "/deleteDish/{id}")
	public String deleteDish(@PathVariable Integer id) {
		if(id!=null){
			Dish d = dishService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getDishes().remove(d);
			dishService.delete(id);
		return "yes";
			}else return "no";	
	}
	@DeleteMapping(path = "/deleteDrink/{id}")
	public String deleteDrink(@PathVariable Integer id) {
		if(id!=null){
			Drink d = drinkService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getDrinks().remove(d);
			drinkService.delete(id);
		return "yes";
			}else return "no";	
	}
	
	@DeleteMapping(path = "/deleteWaiter/{id}")
	public String deleteWaiter(@PathVariable Long id) {
		if(id!=null){
			Waiter w = waiterService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getWaiters().remove(w);
			waiterService.delete(id);
		return "yes";
			}else return "no";	
	}
	
	@DeleteMapping(path = "/deleteCook/{id}")
	public String deleteCook(@PathVariable Long id) {
		if(id!=null){
			Cook c = cookService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getCooks().remove(c);
			cookService.delete(id);
		return "yes";
			}else return "no";	
	}
	
	@DeleteMapping(path = "/deleteBartender/{id}")
	public String deleteBartender(@PathVariable Long id) {
		if(id!=null){
			Bartender b = bartenderService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getBartenders().remove(b);
			bartenderService.delete(id);
		return "yes";
			}else return "no";	
	}
	
	@DeleteMapping(path = "/deleteBidder/{id}")
	public String deleteBidder(@PathVariable Long id) {
		if(id!=null){
			Bidder b = bidderService.findOne(id);
			restaurantService.findOne(restaurantManager.getIdRestaurant()).getBidders().remove(b);
			bidderService.delete(id);
		return "yes";
			}else return "no";	
	}

	@PutMapping(path = "/{id}")
	public RestaurantManager updateResManager(@PathVariable Long id,@RequestBody RestaurantManager resManager) {
	restaurantManagerService.findOne(id);
			
	resManager.setId(id);
		return restaurantManagerService.save(resManager);
	}	
	

	@PutMapping(path = "/update/{id}")
	public Restaurant updateRestaurant(@PathVariable Long id,@RequestBody Restaurant res) {
	restaurantService.findOne(id);
			
	res.setId(id);
		return restaurantService.save(res);
	}
	
	@PutMapping(path = "/updateFoodstuff")
	public Foodstuff updateFoodstuff(@RequestBody Foodstuff food) {
		foodstuffService.findOne(food.getId());			
		food.setId(food.getId());
		return foodstuffService.save(food);
	}
	@PutMapping(path = "/updateDish")
	public Dish updateDish(@RequestBody Dish dish) {
		dishService.findOne(dish.getId());			
		dish.setId(dish.getId());
		return dishService.save(dish);
	}
	@PutMapping(path = "/updateDrink")
	public Drink updateDish(@RequestBody Drink drink) {
		drinkService.findOne(drink.getId());		
		drink.setId(drink.getId());
		return drinkService.save(drink);
	}

		
		

		
	
	
	
	

}