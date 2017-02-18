package com.isa.res.manager;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.Bartender;
import com.isa.bartender.BartenderService;
import com.isa.bidder.*;
import com.isa.cook.Cook;
import com.isa.cook.CookService;
import com.isa.dish.Dish;
import com.isa.drink.Drink;
import com.isa.employed.Employed;
import com.isa.res.manager.*;
import com.isa.restaurant.*;
import com.isa.system.manager.SystemManager;
import com.isa.user.Role;
import com.isa.user.User;
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
	private RestaurantManager restaurantManager;
	
	@Autowired
	public RestaurantManagerController(HttpSession httpSession, final RestaurantService restaurantService,
			final RestaurantManagerService restaurantManagerService,final WaiterService waiterService, final CookService cookService,
			final BartenderService bartenderService,final BidderService bidderService) {
		super();
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.restaurantManagerService = restaurantManagerService;
		this.waiterService = waiterService;
		this.cookService = cookService;
		this.bartenderService = bartenderService;
		this.bidderService = bidderService;
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
			emp.setFirstLogIn(true);
			waiterService.save(emp);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
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
	
	@GetMapping(path = "/Waiters")
	public List<Waiter> findAllWaiters() {
	    List<Waiter> waiters =restaurantService.findOne(restaurantManager.getIdRestaurant()).getWaiters();
		return waiters;
	}
	@GetMapping(path = "/Cooks")
	public List<Cook> findAllCooks() {
	    List<Cook> cooks =restaurantService.findOne(restaurantManager.getIdRestaurant()).getCooks();
		return cooks;
	}
	@GetMapping(path = "/Bartenders")
	public List<Bartender> findAllBartenders() {
	    List<Bartender> bartenders =restaurantService.findOne(restaurantManager.getIdRestaurant()).getBartenders();
		return bartenders;
	}
	@GetMapping(path = "/Bidders")
	public List<Bidder> findAllBidders() {
	    List<Bidder> bidders =restaurantService.findOne(restaurantManager.getIdRestaurant()).getBidders();
		return bidders;
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

		
		

		
	
	
	
	

}