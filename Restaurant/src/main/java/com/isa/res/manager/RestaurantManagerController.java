package com.isa.res.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.isa.Rating;
import com.isa.Report;
import com.isa.Transfer;
import com.isa.Visit;
import com.isa.bartender.Bartender;
import com.isa.bartender.BartenderService;
import com.isa.bidder.Bidder;
import com.isa.bidder.BidderService;
import com.isa.bill.Bill;
import com.isa.cook.Cook;
import com.isa.cook.CookService;
import com.isa.dish.Dish;
import com.isa.dish.DishService;
import com.isa.drink.Drink;
import com.isa.drink.DrinkService;
import com.isa.foodstuf.Foodstuff;
import com.isa.foodstuf.FoodstuffService;
import com.isa.grade.GradeService;
import com.isa.invitation.InvitationService;
import com.isa.invitation.InvitationStatus;
import com.isa.offer.Offer;
import com.isa.offer.OfferService;
import com.isa.offer.StateOffer;
import com.isa.offer.unit.OfferUnitService;
import com.isa.order.Order;
import com.isa.order.OrderService;
import com.isa.ordered.dish.OrderedDish;
import com.isa.res.order.ResOrder;
import com.isa.res.order.ResOrderService;
import com.isa.res.order.unit.ResOrderUnit;
import com.isa.res.order.unit.ResOrderUnitService;
import com.isa.res.segment.ResSegment;
import com.isa.res.segment.ResSegmentService;
import com.isa.res.table.ReonTable;
import com.isa.res.table.ResTable;
import com.isa.res.table.ResTableService;
import com.isa.res.table.SizeTable;
import com.isa.res.table.StateTable;
import com.isa.reservation.Reservation;
import com.isa.reservation.ReservationService;

import com.isa.responsability.Responsability;
import com.isa.responsability.ResponsabilityService;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.user.Role;
import com.isa.waiter.Waiter;
import com.isa.waiter.WaiterService;
import com.isa.work.day.WorkDay;
import com.isa.work.day.WorkDayService;
import com.isa.work.shift.ShiftType;
import com.isa.work.shift.WorkShift;
import com.isa.work.shift.WorkShiftService;




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
	private final ResTableService resTableService;
	private final ResSegmentService resSegmentService;
	private final ResOrderService resOrderService; 
	private final ResOrderUnitService resOrderUnitService;
	private final OfferUnitService offerUnitService;
	private final OfferService offerService;
	private final WorkShiftService workShiftService;
	private final WorkDayService workDayService;
	private final ResponsabilityService responsabilityService;
	private final ReservationService reservationService;
	private final InvitationService invitationService;
	private final GradeService gradeService;
	private final OrderService orderService;
	private RestaurantManager restaurantManager;
	private int visina = 0;
	private int sirina = 0 ;
	private Long sifra;
	@Autowired
	public RestaurantManagerController(HttpSession httpSession, RestaurantService restaurantService,
			RestaurantManagerService restaurantManagerService, WaiterService waiterService, CookService cookService,
			BartenderService bartenderService, BidderService bidderService, FoodstuffService foodstuffService,
			DrinkService drinkService, DishService dishService,ResTableService resTableService,
			ResSegmentService resSegmentService,ResOrderService resOrderService,OfferService offerService,
			ResOrderUnitService resOrderUnitService,OfferUnitService offerUnitService,WorkShiftService workShiftService,
			WorkDayService workDayService,ResponsabilityService responsabilityService,
			ReservationService reservationService,InvitationService invitationService,GradeService gradeService,
			OrderService orderService
			) {
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
		this.resTableService= resTableService;
		this.resSegmentService = resSegmentService;
		this.resOrderService= resOrderService;
		this.offerService= offerService;
		this.resOrderUnitService = resOrderUnitService;
		this.offerUnitService = offerUnitService;
		this.workShiftService = workShiftService;
		this.workDayService = workDayService;
		this.responsabilityService =responsabilityService;
		this.reservationService = reservationService;
		this.invitationService = invitationService;
		this.gradeService = gradeService;
		this.orderService = orderService;
	}

	@GetMapping("/checkRights")
	public RestaurantManager checkRights() {		
			 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		return restaurantManager;
			
	}
	
	@PostMapping(path = "/newWaiter")
	public String saveEmployee(@RequestBody Waiter emp) {
		//System.out.println("uslo");
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		if (emp != null){
			emp.setFirstLogIn(true);			
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());	
			emp.setRestaurantId(r.getId());
			waiterService.save(emp);
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
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		if (emp != null){
			emp.setFirstLogIn(true);						
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			emp.setRestaurantId(r.getId());
			cookService.save(emp);
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
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		if (emp != null){
			emp.setFirstLogIn(true);			
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			emp.setRestaurantId(r.getId());
			bartenderService.save(emp);
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
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
		 restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
		restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
		restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		Restaurant r = new Restaurant();
		try {
			r  =restaurantService.findOne(restaurantManager.getIdRestaurant());
			 
		} catch (Exception e) {
		
		}
	
  return r;
	}
	@GetMapping(path = "/dishes")
	public List<Dish> findAllDishes() {
			restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
	@GetMapping(path = "/table/{id}")
	public ResTable findTable(@PathVariable Long id) {
		ResTable table =resTableService.findOne(id);
		return table;
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
		restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		if(id!=null){
			try {
				Dish d = dishService.findOne(id);
				restaurantService.findOne(restaurantManager.getIdRestaurant()).getDishes().remove(d);
				dishService.delete(id);
				return "yes";
				
			} catch (Exception e) {
				 return "no";// TODO: handle exception
			}
			}else return "no";	
	}
	@DeleteMapping(path = "/deleteDrink/{id}")
	public String deleteDrink(@PathVariable Integer id) {
		if(id!=null){
			try {
				Drink d = drinkService.findOne(id);
				restaurantService.findOne(restaurantManager.getIdRestaurant()).getDrinks().remove(d);
				drinkService.delete(id);
				return "yes";
			} catch (Exception e) {
				return "no";
				// TODO: handle exception
			}
			
		
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
		restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
		try{
		restaurantManagerService.save(resManager);
		httpSession.setAttribute("user", resManager);
	} catch(Exception e) {
		return null;
	}
	
	
		return resManager;
	}	
	

	@PutMapping(path = "/update/{id}")
	public Restaurant updateRestaurant(@PathVariable Long id,@RequestBody Restaurant res) {
		restaurantManager =  (RestaurantManager) httpSession.getAttribute("user");
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
		
	@PutMapping(path = "/makeConfig/{widht}/{height}")
	public void makeConfig(@PathVariable("widht") Integer width, @PathVariable("height") Integer height){
		//System.out.println("uslo je konfig");
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());
		//System.out.println("restoran je "+restaurant.getId());
		//System.out.println("da li je prazna lista segmenata "+restaurant.getSegments().isEmpty());
		List <Long>sifre = new ArrayList<Long>();
		for (ResSegment r : restaurant.getSegments()){
			sifre.add(r.getId());
		}
		restaurant.getSegments().clear();
		//System.out.println("da li je prazna lista segmenata "+restaurant.getSegments().isEmpty());
		restaurantService.save(restaurant);
		for (ResSegment r : resSegmentService.findAll()){
			if(sifre.contains(r.getId())){
				resSegmentService.delete(r.getId());
			}
		}
	
		if(restaurant.getSegments().isEmpty()){
			ResSegment s = new ResSegment();
			s.setSegType("inside");
			s.setColor("#00bfff");
			restaurant.getSegments().add(s);
			restaurantService.save(restaurant);	
		}
		//System.out.println("x "+width);
		//System.out.println("y "+height);
		List <ResTable >rt = new ArrayList <ResTable>();
		ResSegment rs = new ResSegment();
		for (ResSegment r : restaurant.getSegments()){
			rs = r;
		}
		visina = height;
		sirina = width;
		Long id = rs.getId();
		for(int x = 0; x<width; x++){
			for(int y  =0; y<height; y++){
				ResTable t = new ResTable();
				t.setSize(SizeTable.four);
				t.setState(StateTable.exists);
				t.setxPos(x);
				t.setyPos(y);
				if(y %2==0){
					t.setReon(ReonTable.first);
				}else{
					t.setReon(ReonTable.second);
				}
				t.setSegment("inside");
				t.setSegColor("#00bfff");
				resTableService.save(t);
				System.out.println(rs.getId());				
				rt.add(t);	
			}
		}
		rs.setTables(rt);
		rs.setId(id);
		resSegmentService.save(rs);
	}
	
	@GetMapping(path = "/segments")
	public List<ResSegment> findAllSegments() {
	    List<ResSegment> ss =restaurantService.findOne(restaurantManager.getIdRestaurant()).getSegments();
		return ss;
	}
	
	@PostMapping(path = "/addSegment")
	public String saveSegment(@RequestBody ResSegment segment) {
		//System.out.println("uslo");
		if (segment != null){
		//	System.out.println("uslo je pravljenje");
			resSegmentService.save(segment);
			Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
			//System.out.println(r.getId());
			r.getSegments().add(segment);
			restaurantService.save(r);	
		return "dodato";
	}
		else
			return "nije";		
	}
		
	
	@GetMapping(path="/getTables")
	public List<ResTable> getTables(){
		ArrayList<ResTable> outTables = new ArrayList<ResTable>();
		ArrayList<ResTable> resultTables = new ArrayList<ResTable>();
		try {
		Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());		
		for(int i=0; i<r.getSegments().size(); i++){
			outTables.addAll(r.getSegments().get(i).getTables());
			
		}
		Date today = new Date();
		
		for(ResTable rt: outTables){
			for(Reservation reservation:rt.getReservations()){
				if(reservation.getDate().after(today)){
					rt.setEnableDel(false);				
				}else
				{
					rt.setEnableDel(true);
				}
			}
		}
		if(visina==0 || sirina==0){
			for(ResTable tt : outTables){
				if(tt.getxPos()>visina){
					visina = tt.getxPos();
				}
				if(tt.getyPos()>sirina){
					sirina = tt.getyPos();
				}
				
			}
			visina = visina + 1;
			sirina = sirina + 1;
			
		}
		
		for(int i =0;i<visina ;i++){
			for (int j=0;j<sirina;j++){
				for(ResTable rr : outTables){
					if(rr.getxPos()==i && rr.getyPos()==j){
						resultTables.add(rr);
					}

				}
			}
		}
		/*for(ResTable rr : resultTables){
			System.out.println(rr.getxPos() +" , "+rr.getyPos() );
			System.out.println("---------------------------");
		}*/
		
		
		return resultTables;
		
		} catch (Exception e) {			
			return resultTables;
		}
	}
	@PutMapping(path = "/updateTable")
	public ResTable updateTable(@RequestBody ResTable resTable) {
		ResTable rt = resTableService.findOne(resTable.getId());
		Integer brojac=0;
		Integer brojac1 = 0;
		for (ResSegment rs :resSegmentService.findAll()){
			for(ResTable rtt : rs.getTables()){
				if(rtt.getId()==rt.getId()){
					rs.getTables().remove(rtt);
					resSegmentService.save(rs);
					break;
				}
				brojac1++;
			}
			brojac++;
		}
		String ime = resTable.getSegment();	
		ResSegment resSegment = resSegmentService.findbySegType(ime);
		resTable.setSegColor(resSegment.getColor());
		resSegment.getTables().add(rt);
		resSegmentService.save(resSegment);
		resTable.setId(resTable.getId());

		return resTableService.save(resTable);
	}
	
	@GetMapping(path = "/resOrders")
	public List<ResOrder> findAllResOrders() {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List<ResOrder>result = new ArrayList<ResOrder>();
		try {
			result = restaurant.getOrders();		
		} catch (Exception e) {
	
		}
		return result;
		
	}
	
	@GetMapping(path = "/resOrdersResOrderUnits/{id}")
	public List<ResOrderUnit> findResOrderUnitsForResOrder(@PathVariable Long id) {
		ResOrder resOrder = resOrderService.findOne(id);
		List<ResOrderUnit> result = new ArrayList <ResOrderUnit>();
		try {
			result  = resOrder.getResOrderFoodstuffs();
		} catch (Exception e) {
		}
		return result;
	}
	@PostMapping(path = "/FirstTimeResOrder")
	public Long makeFirstTimeResOrder() {
		ResOrder resOrder = new ResOrder();
		resOrderService.save(resOrder);
		Long id = Long.parseLong("1");

		for(ResOrder r : resOrderService.findAll()){
			if(r.getEndDate()==null){
				System.out.println("sifra prvog pravljenja "+r.getId());
				id = r.getId();
				sifra = id;
				break;
			}
		}	
		 return id;
	}
	
	@PostMapping(path = "/saveResOrderUnit")
	public List<ResOrderUnit> saveResOrderUnit(@RequestBody ResOrderUnit resOrderUnit) {
		//System.out.println("uslo u cuvanje");
		resOrderUnit.setResOrder(sifra);
		//System.out.println("sifra "+resOrderUnit.getResOrder());
		//System.out.println("naziv "+resOrderUnit.getOrderFoodstuff());
		//System.out.println("kolicina "+resOrderUnit.getOrderQuantity());
		ResOrder resOrder = resOrderService.findOne(resOrderUnit.getResOrder());
		resOrder.getResOrderFoodstuffs().add(resOrderUnit);
		resOrderService.save(resOrder);
		List <ResOrderUnit> r = resOrder.getResOrderFoodstuffs();
		
		return r;
	}
	@PutMapping(path = "/finalSaveResOrder")
	public ResOrder finalSaveResOrder(@RequestBody ResOrder resOrder) {
		String[] dpTokens = resOrder.getEndDate().split("-");
	
		String newDate = dpTokens[2]+"/"+dpTokens[1]+"/"+dpTokens[0];
		resOrder.setId(sifra);
		resOrder.setEndDate(newDate);
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List <ResOrderUnit> newUnits = new ArrayList<ResOrderUnit>();
		 for(ResOrderUnit r : resOrderUnitService.findAll()){
			 if(r.getResOrder()==resOrder.getId()){
				 newUnits.add(r);
			 }
		 }
		
		 resOrder.setResOrderFoodstuffs(newUnits);
		 resOrder.setResName(restaurant.getName());
		 resOrder.setId(resOrder.getId());
		resOrderService.save(resOrder);
		restaurant.getOrders().add(resOrder);
		restaurant.setId(restaurant.getId());
		restaurantService.save(restaurant);
	
		return resOrderService.save(resOrder);
	}
	
	@DeleteMapping(path = "/cancelResOrderUnit")
	public String cancelResOrderUnit() {
		if(sifra!=null){
			
			ResOrder resOr = resOrderService.findOne(sifra);
			resOrderService.delete(resOr.getId());
			
		return "yes";
			}else return "no";	
	}

	@GetMapping(path = "/allActualOffers/{id}")
	public List<Offer> findAllOffers(@PathVariable Long id) {
		List<Offer> result = new ArrayList<Offer>();		
		for(Offer o : offerService.findAll()){
			if(o.getIdResOrder()==id){
				result.add(o);
			}
		}	
		return result;
	}
	
	
	@PutMapping(path = "/acceptOffer/{id}")
	public String acceptOffer(@PathVariable Long id) {
		Offer offer = offerService.findOne(id);
		ResOrder ro = resOrderService.findOne(offer.getIdResOrder());
		Date today = new Date();
		String todayString = today.toString();
		System.out.println("danas je "+todayString);
		ro.setEndDate(todayString);
		ro.setId(ro.getId());
		resOrderService.save(ro);
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		restaurant.setId(restaurant.getId());
		restaurantService.save(restaurant);
		if(id!=null){
			for(Offer o : offerService.findAll()){
				if(o.getIdResOrder()==ro.getId()){
					if(o.getId()==id){
						o.setSeen("nije");
						o.setAccepted(StateOffer.yes);
						o.setId(o.getId());
						offerService.save(o);					
						Bidder b = bidderService.findOne(o.getIdBidder());
						for(Offer of:b.getOffers()){
							if(of.getId()==id){
								of.setSeen("nije");
							}
						}
						b.setId(b.getId());
						bidderService.save(b);
					}else{
						o.setSeen("nije");
						o.setAccepted(StateOffer.no);
						o.setId(o.getId());
						offerService.save(o);
						Bidder b = bidderService.findOne(o.getIdBidder());
						for(Offer of:b.getOffers()){
							if(of.getId()==o.getId()){
								of.setSeen("nije");
							}
						}
						b.setId(b.getId());
						bidderService.save(b);
					}
					
				}
			
			}
			restaurant.setId(restaurant.getId());
			restaurantService.save(restaurant);
			return "ok";
		}else{
			
			return "nije_ok";
		}
		
	}
	@GetMapping(path = "/getAllShifts")
	public List<WorkShift> getSchedule() {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());					
		List<WorkShift> allShifts= new ArrayList<WorkShift>();
		try {
	
		for(WorkDay day : restaurant.getWorkDays()) {			
			for(WorkShift shift : day.getWorkShifts()) {
					allShifts.add(shift);
			}
		
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return allShifts;	
	}
	

	@PostMapping(path = "/MakeFirstShift")
	public String MakeFirstShift(@RequestBody Transfer sve) {
		if(sve!=null){
				String cooks = sve.getCooks();
				String bartenders = sve.getBartenders();
				Integer smena = sve.getShift();
				String datum = sve.getDate();
				String firstReon = sve.getFirstReon();
				String secondReon = sve.getSecondReon();
				
				List<Cook> kuvari = new ArrayList<Cook>();
				List<Waiter> konobari = new ArrayList<Waiter>();
				List<Waiter> konobariFirst = new ArrayList<Waiter>();
				List<Waiter> konobariSecond = new ArrayList<Waiter>();
				List<Bartender> barmeni = new ArrayList<Bartender>();
				
				String bezPoslednjegKuvar = cooks.replace(cooks.substring(cooks.length()-1), "");
				String bezPoslednjegBarmen = bartenders.replace(bartenders.substring(bartenders.length()-1), "");
				String bezPoslednjegFirstReon = firstReon.replace(firstReon.substring(firstReon.length()-1), "");
				String bezPoslednjegSecondReon = secondReon.replace(secondReon.substring(secondReon.length()-1), "");

				String [] parsiranoKuvar = bezPoslednjegKuvar.split(",");
				String [] parsiranoBarmen = bezPoslednjegBarmen.split(",");
				String [] parsiranoFirstReon = bezPoslednjegFirstReon.split(",");
				String [] parsiranoSecondReon = bezPoslednjegSecondReon.split(",");
				
				List<Long> sifreKuvara= new ArrayList<Long>();
				List<Long> sifreKonobara= new ArrayList<Long>();
				List<Long> sifreBarmena= new ArrayList<Long>();
				List<Long> sifreKonobaraFirst= new ArrayList<Long>();
				List<Long> sifreKonobaraSecond= new ArrayList<Long>();
				/////////////////////////////////////////////////////////////////
				for(String s:parsiranoKuvar){
					sifreKuvara.add(Long.parseLong(s));
				}
				for(String s:parsiranoFirstReon){
					sifreKonobaraFirst.add(Long.parseLong(s));
					if(!sifreKonobara.contains(s)){
						sifreKonobara.add(Long.parseLong(s));
					}
				}
				for(String s:parsiranoSecondReon){
					sifreKonobaraSecond.add(Long.parseLong(s));	
					if(!sifreKonobara.contains(s)){
						sifreKonobara.add(Long.parseLong(s));
					}
				}
				
				for(String s:parsiranoBarmen){
					sifreBarmena.add(Long.parseLong(s));
				}
				////////////////////////////////////////////////////////////////
				for(Long sifra:sifreKuvara){
					kuvari.add(cookService.findOne(sifra));
				}

				for(Long sifra:sifreBarmena){
					barmeni.add(bartenderService.findOne(sifra));
				}
				for(Long sifra:sifreKonobaraFirst){
					konobariFirst.add(waiterService.findOne(sifra));
					
				}
				for(Long sifra:sifreKonobaraSecond){
					konobariSecond.add(waiterService.findOne(sifra));				
				}
				for(Long sifra:sifreKonobara){
					konobari.add(waiterService.findOne(sifra));				
				}
				
				///////////////////////////////////////////////////////////////
				Responsability first = new Responsability();
				first.setReon(ReonTable.first);
				first.setWaiters(konobariFirst);
				responsabilityService.save(first);
				
				Responsability second = new Responsability();
				second.setReon(ReonTable.second);
				second.setWaiters(konobariSecond);
				responsabilityService.save(second);
				
					
				Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String[] dpTokens = datum.split("-");
				Date workDay = null; 
				try {
					workDay = sdf.parse(dpTokens[0]+"-"+dpTokens[1]+"-"+dpTokens[2]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ShiftType tip;
				WorkShift ws = new WorkShift();
				ws.setDay(workDay);
				if(ws.getResponsabilites()!=null){
					ws.getResponsabilites().add(first);
					ws.getResponsabilites().add(second);
				}else{
					List<Responsability> lista = new ArrayList<Responsability>();
					lista.add(first);
					lista.add(second);
					ws.setResponsabilites(lista);
				}
				if(smena==1){
					ws.setShiftType(ShiftType.firstShift);
					ws.setStartTime("08:00:00");
					ws.setEndTime("13:00:00");
					tip = ShiftType.firstShift;
				}else if(smena==2){
					ws.setShiftType(ShiftType.secondShift);
					ws.setStartTime("13:00:01");
					ws.setEndTime("18:00:00");
					tip=ShiftType.secondShift;
					
				}else{
					ws.setShiftType(ShiftType.thirdShift);
					ws.setStartTime("18:00:01");
					ws.setEndTime("23:00:00");
					tip = ShiftType.thirdShift;
				}
				
				ws.setCooks(kuvari);
				ws.setWaiters(konobari);
				ws.setBartenders(barmeni);
				
				int uslo = 0;
				int brojac = 0;
				int usloo = 0;
			///////////////////////////////
				
				////////////////////////////////
				for(WorkDay w:restaurant.getWorkDays()){
					if(w.getDay().equals(workDay)){
						uslo++;
						List<WorkShift> smene = new ArrayList<WorkShift>();
						for(WorkShift workShift:w.getWorkShifts()){
							if(workShift.getDay().equals(workDay) && workShift.getShiftType()==tip){
								System.out.println("usloo");
								workShiftService.delete(workShift.getId());
							}else{
								smene.add(workShift);
							}
							
						}
						w.setWorkShifts(smene);
						workShiftService.save(ws);
						w.getWorkShifts().add(ws);
						w.setId(w.getId());
						workDayService.save(w);
						
						restaurant.getWorkDays().get(brojac).setId(w.getId());
						restaurantService.save(restaurant);
						
					}
					brojac++;
				}
				if(uslo==0){
					workShiftService.save(ws);
					List<WorkShift>smene = new ArrayList<WorkShift>();
					smene.add(ws);
					WorkDay newDay = new WorkDay();
					newDay.setDay(workDay);
					newDay.setWorkShifts(smene);
					workDayService.save(newDay);
					restaurant.getWorkDays().add(newDay);
					restaurantService.save(restaurant);
			
			}
			
			return "dodato";
		}
			else
				return "nije";
					
		}
	
	private List<String> getYesterdayDateString() {
		List<String> datumi = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 7;i>0;i--){
        	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DATE, -i);
        	datumi.add(dateFormat.format(cal.getTime()));
        }
		
		 return datumi;
     
	}
	
	private List<String> getMonthDateString() {
		List<String> datumi = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 31;i>0;i--){
        	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DATE, -i);
        	datumi.add(dateFormat.format(cal.getTime()));
        }
		
		 return datumi;
     
	}
	@GetMapping(path = "/getDayBusiness/{datum}")
	public List<Bill> getDayBusiness(@PathVariable String datum) {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List<Bill> allDayBills= new ArrayList<Bill>();
		Bill b1 = new Bill();
		Bill b2 = new Bill();
		Bill b3 = new Bill();
		Bill b4 = new Bill();
		Long suma1 =Long.parseLong("0");
		Long suma2 =Long.parseLong("0");
		Long suma3 =Long.parseLong("0");
		Long suma4=Long.parseLong("0");
		String vreme1 ="12:00:00";
		String vreme2="16:00:00"; 
		String vreme3 ="20:00:00";
		String vreme4 ="23:00:00";
		for(Bill bill:restaurant.getBills()){
			
			if(bill.getBillDate().equals(datum)){
				String [] tokens = bill.getTime().split(":");
				if(Integer.parseInt(tokens[0])<12){
					suma1=suma1+bill.getTotalPrice();
				}else if(Integer.parseInt(tokens[0])>=12 &&Integer.parseInt(tokens[0])<16){
					suma2=suma2+bill.getTotalPrice();
				}else if(Integer.parseInt(tokens[0])>=16 &&Integer.parseInt(tokens[0])<20){
					suma3=suma3+bill.getTotalPrice();
				}else{
					suma4=suma4+bill.getTotalPrice();
				}
				
			}
		}
		b1.setBillDate(datum);
		b1.setTime(vreme1);
		b1.setTotalPrice(suma1);
		
		b2.setBillDate(datum);
		b2.setTime(vreme2);
		b2.setTotalPrice(suma2);
		
		b3.setBillDate(datum);
		b3.setTime(vreme3);
		b3.setTotalPrice(suma3);
		
		b4.setBillDate(datum);
		b4.setTime(vreme4);
		b4.setTotalPrice(suma4);
		
		allDayBills.add(b1);
		allDayBills.add(b2);
		allDayBills.add(b3);
		allDayBills.add(b4);
		
		return allDayBills;	
	}
	
	@GetMapping(path = "/getWeekBusiness")
	public List<Bill> getWeekBusiness() {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List<Bill> allDayBills= new ArrayList<Bill>();
		List<String> datumi =getYesterdayDateString();
		
		for(String datum:datumi){
			Bill b = new Bill();
			Long suma =Long.parseLong("0");
			for(Bill bill:restaurant.getBills()){
				if(bill.getBillDate().equals(datum)){
					suma=suma+bill.getTotalPrice();
				}
				
			}
			b.setTotalPrice(suma);
			b.setBillDate(datum);
			allDayBills.add(b);
		}
		return allDayBills;	
	}
	
	
	@GetMapping(path = "/getWeekBusinessForWaiters")
	public List<Report> getWeekBusinessForWaiters() {

		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List<Report> allReports= new ArrayList<Report>();
		List<Report> result= new ArrayList<Report>();
		List<String> datumi =getYesterdayDateString();
		List<Long>sifreKonobar = new ArrayList<Long>();
		for(String datum:datumi){
			for(Bill bill:restaurant.getBills()){
				if(bill.getBillDate().equals(datum)){
					if(!sifreKonobar.contains(bill.getWaiterId())){
						sifreKonobar.add(bill.getWaiterId());
					}
				}
				
			}
		}
		for(Long sifra:sifreKonobar){
			Report r = new Report();
			Long suma = Long.parseLong("0");
			for(String datum:datumi){
				
				
				for(Bill bill:restaurant.getBills()){
					if(bill.getBillDate().equals(datum)){
						if(sifra.equals(bill.getWaiterId())){
							suma = suma+ bill.getTotalPrice();
							
						}
						
					}
					
				}				
			}
		
			
			r.setWaiter(sifra);
			r.setIncome(suma);
			allReports.add(r);
		}
		
		for(Report r:allReports){
			r.setWaiterName(waiterService.findOne(r.getWaiter()).getFirstName()+" "+
					waiterService.findOne(r.getWaiter()).getLastName());
		}
		
		return allReports;	
	}
	
	@GetMapping(path = "/getDayVisits/{datum}")
	public List<Visit> getDayVisits(@PathVariable String datum) {
		//samo zbog prenosa je pogodno
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dpTokens = datum.split("-");
		Date workDay = null; 
		try {
			workDay = sdf.parse(dpTokens[0]+"-"+dpTokens[1]+"-"+dpTokens[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Visit> result = new ArrayList<Visit>();
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		Visit v1 = new Visit();
		Visit v2 = new Visit();
		String vreme1 = "16:00:00"; 
		String vreme2 ="23:00:00";
		int broj_rez1 = 0;
		int broj_inv1 = 0;
		int broj_rez2 = 0;
		int broj_inv2 = 0;
		for(Reservation r: reservationService.findAll()){
			if(r.getResId().equals(restaurant.getId())){
				if(r.getDate().equals(workDay)){
						String [] vreme = r.getStartTime().split(":");
						if(Integer.parseInt(vreme[0])<17){
						broj_rez1 = broj_rez1+1;
						broj_inv1 = broj_inv1+ invitationService.findAllByReservationIdAndStatus(r.getId(), InvitationStatus.accepted).size();
						}else{
						broj_rez2 = broj_rez2+1;
						broj_inv2 = broj_inv2+ invitationService.findAllByReservationIdAndStatus(r.getId(), InvitationStatus.accepted).size();	
							
						}
				}
			}
			
		}
		v1.setDate(vreme1);
		v1.setNumber(broj_rez1+broj_inv1);
		v2.setDate(vreme2);
		v2.setNumber(broj_rez2+broj_inv2);
		result.add(v1);
		result.add(v2);

		return result;
	}
	
	@GetMapping(path = "/getWeekVisit")
	public List<Visit> getWeekVisits() {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		List<Visit> result = new ArrayList<Visit>();
		List<String> datumi =getYesterdayDateString();
		for(String datum:datumi){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] dpTokens = datum.split("-");
			Date workDay = null; 
			try {
				workDay = sdf.parse(dpTokens[0]+"-"+dpTokens[1]+"-"+dpTokens[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Visit v = new Visit();
			int sumaRes = 0;
			int sumaInv = 0;
			for(Reservation r: reservationService.findAll()){
				if(r.getResId().equals(restaurant.getId())){
					if(r.getDate().equals(workDay)){
						
							sumaRes = sumaRes+1;
							sumaInv = sumaInv+ invitationService.findAllByReservationIdAndStatus(r.getId(), InvitationStatus.accepted).size();	
					}
				}
				
			}
			v.setDate(datum);
			v.setNumber(sumaRes+sumaInv);
			result.add(v);
		}

		return result;
	}
	
	@GetMapping(path = "/getWeekRatings/{waiter}/{dish}")
	public Rating getWeekRatings(@PathVariable("waiter") Long waiter, @PathVariable("dish") Integer dish) {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		
		List<String> datumi =getYesterdayDateString();
		
		int brojacDish = 0;
		Double sumaDish = 0.0;
		int brojacWaiter = 0;
		Double sumaWaiter = 0.0;
		int brojacRes = 0;
		Double sumaRes = 0.0;
		
		for(String datum:datumi){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] dpTokens = datum.split("-");
			Date workDay = null; 
			try {
				workDay = sdf.parse(dpTokens[0]+"-"+dpTokens[1]+"-"+dpTokens[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			for(Order o:orderService.findByOrderDateAndRestaurantId(workDay, restaurant.getId())){
				Double ocenaDish = gradeService.findByOrderId(o.getId()).getOrderGrade();
				Double ocenaWaiter = gradeService.findByOrderId(o.getId()).getWaiterGrade();
				Double ocenaRes = gradeService.findByOrderId(o.getId()).getRestaurantGrade();
				for(OrderedDish od:o.getOrderedDish()){
					if(od.getDishId().equals(dish)){
						sumaDish = sumaDish+ocenaDish;
						brojacDish++;
					}
				}
				if(o.getWaiterId().equals(waiter)){
					sumaWaiter = sumaWaiter+ocenaWaiter;
					brojacWaiter++;
				}
				sumaRes = sumaRes+ocenaRes;
				brojacRes++;	
			}

		}
		Double prosekDish = sumaDish/brojacDish;
		
		Double prosekWaiter = sumaWaiter/brojacWaiter;
		
		Double prosekRes = sumaRes/brojacRes;
		
		Rating result = new Rating();
		
		result.setDish(dishService.findOne(dish).getName());
		result.setDishRating(prosekDish);
		result.setWaiter(waiterService.findOne(waiter).getFirstLogIn()+" "+waiterService.findOne(waiter).getLastName());
		result.setWaiterRating(prosekWaiter);
		result.setRestaurant(restaurant.getName());
		result.setResRating(prosekRes);
		
		
		
	return result;
	}
	/////
	@GetMapping(path = "/getMonthRatings/{waiter}/{dish}")
	public Rating getMonthRatings(@PathVariable("waiter") Long waiter, @PathVariable("dish") Integer dish) {
		Restaurant restaurant = restaurantService.findOne(restaurantManager.getIdRestaurant());	
		
		List<String> datumi =getMonthDateString();
		int brojacDish = 0;
		Double sumaDish = 0.0;
		int brojacWaiter = 0;
		Double sumaWaiter = 0.0;
		int brojacRes = 0;
		Double sumaRes = 0.0;
		
		for(String datum:datumi){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] dpTokens = datum.split("-");
			Date workDay = null; 
			try {
				workDay = sdf.parse(dpTokens[0]+"-"+dpTokens[1]+"-"+dpTokens[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			for(Order o:orderService.findByOrderDateAndRestaurantId(workDay, restaurant.getId())){
				Double ocenaDish = gradeService.findByOrderId(o.getId()).getOrderGrade();
				Double ocenaWaiter = gradeService.findByOrderId(o.getId()).getWaiterGrade();
				Double ocenaRes = gradeService.findByOrderId(o.getId()).getRestaurantGrade();
				for(OrderedDish od:o.getOrderedDish()){
					if(od.getDishId().equals(dish)){
						sumaDish = sumaDish+ocenaDish;
						brojacDish++;
					}
				}
				if(o.getWaiterId().equals(waiter)){
					sumaWaiter = sumaWaiter+ocenaWaiter;
					brojacWaiter++;
				}
				sumaRes = sumaRes+ocenaRes;
				brojacRes++;	
			}

		}
		Double prosekDish = sumaDish/brojacDish;
		
		Double prosekWaiter = sumaWaiter/brojacWaiter;
		
		Double prosekRes = sumaRes/brojacRes;
		
		Rating result = new Rating();
		
		result.setDish(dishService.findOne(dish).getName());
		result.setDishRating(prosekDish);
		result.setWaiter(waiterService.findOne(waiter).getFirstLogIn()+" "+waiterService.findOne(waiter).getLastName());
		result.setWaiterRating(prosekWaiter);
		result.setRestaurant(restaurant.getName());
		result.setResRating(prosekRes);
		
		
		
	return result;
	}
	
}