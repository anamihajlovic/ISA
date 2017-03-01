package com.isa.bidder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.Bartender;
import com.isa.dish.Dish;
import com.isa.offer.Offer;
import com.isa.offer.OfferService;
import com.isa.offer.StateOffer;
import com.isa.offer.unit.OfferUnit;
import com.isa.offer.unit.OfferUnitService;
import com.isa.res.manager.RestaurantManager;
import com.isa.res.manager.RestaurantManagerController;
import com.isa.res.order.ResOrder;
import com.isa.res.order.ResOrderService;
import com.isa.res.order.unit.ResOrderUnit;
import com.isa.res.order.unit.ResOrderUnitService;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;


@RestController
@RequestMapping("/bidder")
public class BidderController {
	
	private final BidderService bidderService;
	private final RestaurantService restaurantService;
	private final ResOrderService resOrderService; 
	private final ResOrderUnitService resOrderUnitService;
	private final OfferUnitService offerUnitService;
	private final OfferService offerService;
	private HttpSession httpSession;
	private Bidder bidder ;
	private List <Long> sifre;
	private Long idResOrder;
	private Long price = null;

	@Autowired
	public BidderController(final HttpSession httpSession, final BidderService bidderService,
			final RestaurantService restaurantService,ResOrderService resOrderService,OfferService offerService,
			ResOrderUnitService resOrderUnitService,OfferUnitService offerUnitService){
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.httpSession = httpSession;
		this.resOrderService= resOrderService;
		this.offerService= offerService;
		this.resOrderUnitService = resOrderUnitService;
		this.offerUnitService = offerUnitService;

	}

	@GetMapping("/checkRights")
	public Bidder checkRights() {		
		bidder =  (Bidder) httpSession.getAttribute("user");
		return bidder;
			
	}
	

	@PutMapping(path = "/{id}")
	public Bidder updateBidder(@PathVariable Long id,@RequestBody Bidder bidd) {
		try{
			bidderService.save(bidd);
			httpSession.setAttribute("user", bidd);
		} catch(Exception e) {
			return null;
		}
		
		
			return bidd;
	}	
	
	@PutMapping(path = "/changePassword")
	public Bidder changeBidderPassword(@RequestBody Bidder bidder) {
		
		if(bidder.getFirstLogIn())
			bidder.setFirstLogIn(false);
		try{
			bidderService.save(bidder);
			httpSession.setAttribute("user", bidder);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre ponudjaca.");
			return null;
		}
		
		return bidder;	
	}
	
	@GetMapping(path = "/bidderOrders")
	public List<ResOrder> findBidderOrders() {
		List<ResOrder> result = new ArrayList <ResOrder>();
		System.out.println("uslo u pronalazenje porudzbina");
		try {
		Date today = new Date();		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
			for(ResOrder r :resOrderService.findAll()){	
				String[] dpTokens = r.getEndDate().split("/");
				Date dateEnd = null; 
				try {
					dateEnd = sdf.parse(dpTokens[0]+"/"+dpTokens[1]+"/"+dpTokens[2]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dateEnd.after(today)){

					result.add(r);
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@GetMapping(path = "/bidderOfferUnits/{id}")
	public List<OfferUnit> findBidderOfferUnits(@PathVariable Long id) {
		ResOrder resOrder = resOrderService.findOne(id);
		idResOrder = id;
		List<OfferUnit> units = new ArrayList <OfferUnit>();
		sifre = new ArrayList<Long>();
		Long sifrica ;
		for(ResOrderUnit rou:resOrder.getResOrderFoodstuffs()){
			sifre.add(rou.getId());
		}
		for(Long s:sifre){
			System.out.println("sifra "+s);
		}
		if(sifre.size()>1){
		for(OfferUnit ou:offerUnitService.findAll()){
		
			if(sifre.contains(ou.getId())){
				units.add(ou);
				System.out.println(ou.getId());
				
			}
		}
		}else{
			for(OfferUnit ou:offerUnitService.findAll()){			
				if(sifre.get(0).equals(ou.getId())){
					units.add(ou);
					System.out.println(ou.getId());
					
				}
			}
			
		}
		
		return units;
	}
	
	@GetMapping(path = "/showOfferUnit/{id}")
	public OfferUnit showBidderOfferUnit(@PathVariable Long id) {		
		return offerUnitService.findOne(id);	
	}
	@PutMapping(path = "/updateOfferUnit")
	public OfferUnit updateOfferUnit(@RequestBody OfferUnit unit) {
		offerUnitService.findOne(unit.getId());
		unit.setId(unit.getId());
		if(unit.getPrice()!=null){					
			for(Offer offer:bidder.getOffers()){
				for(int i =0;i<offer.getOfferUnits().size();i++){
					if(offer.getOfferUnits().get(i).getId()==unit.getId()){
							
							offer.getOfferUnits().get(i).setPrice(unit.getPrice());
							offer.setId(offer.getId());
							offerService.save(offer);
							
							Long ukupno =Long.parseLong("0");
							for(OfferUnit u :offer.getOfferUnits()){
								ukupno+=u.getPrice();
							}
							price = ukupno;
						offer.setTotalPrice(ukupno);
					
						offer.setId(offer.getId());
						offerService.save(offer);
						Bidder b = bidderService.findOne(offer.getIdBidder());
						b.setId(b.getId());
						bidderService.save(b);
						break;
					}
				}
				
			}
			
			
			
			
		}
		return 	offerUnitService.save(unit);
	}	
	
	@PostMapping(path = "/makeOffer")
	public String makeOffer(@RequestBody Offer offer) {
		//System.out.println("uslo");
		if (offer != null){
			
			for(Long s :sifre){
				if(offerUnitService.findOne(s).equals(null)){
					return "nije";
				}
			}
			Long ukupno =Long.parseLong("0");
			List<OfferUnit> units = new ArrayList<OfferUnit>();
			for(Long s :sifre){
				units.add(offerUnitService.findOne(s));
				ukupno+=offerUnitService.findOne(s).getPrice();
			}

			offer.setAccepted(StateOffer.waiting);
			offer.setTotalPrice(ukupno);
			offer.setOfferUnits(units);
			offer.setIdResOrder(idResOrder);
			offer.setIdBidder(bidder.getId());
			offerService.save(offer);
			bidder.getOffers().add(offer);
			bidder.setId(bidder.getId());
			bidderService.save(bidder);
			System.out.println("u roditelju stoji : "+offer.getOfferUnits().get(0).getPrice());
		return "dodato";
	}
		else
			return "nije";		
	}
	@GetMapping(path = "/allOffers")
	public List<Offer> findAllOffers() {
		List<Offer> result = new ArrayList<Offer>();
		for(Offer o:offerService.findAll()){
			if(o.getIdBidder()==bidder.getId()){
				result.add(o);
			}
		}
		return result;
	}
	
	@GetMapping(path = "/bidderOffer/{id}")
	public Offer showBidderOffer(@PathVariable Long id) {
		Offer o = new Offer();
		o.setId(null);
		ResOrder ro = resOrderService.findOne(id);
		for(Offer offer: offerService.findAll()){
			if(offer.getIdResOrder()==ro.getId()){
				o=offer;
			}
		}
		
		return o;	
	}
	@PutMapping(path = "/updateOffer")
	public Offer updateOffer(@RequestBody Offer offer) {
		if(price!=null){
			offer.setTotalPrice(price);
		}
	
		List<OfferUnit> newUnits = new ArrayList<OfferUnit>();
		for(OfferUnit ou:offerUnitService.findAll()){
			if(sifre.contains(ou.getId())){
				newUnits.add(ou);
			}
		}
		offerService.findOne(offer.getId());
	
		offer.setOfferUnits(newUnits);
		offer.setId(offer.getId());
		
		Bidder b = bidderService.findOne(offer.getIdBidder());
		b.getOffers().add(offer);
		b.setId(b.getId());
		bidderService.save(b);
		return offerService.save(offer);
		

	}
	
	@GetMapping(path = "/getAlert")
	public List<Offer> getAlerts() {
		List<Offer> result = new ArrayList<Offer> ();

			for(Offer o: bidder.getOffers()){			
				
				if(o.getSeen().equals("nije")){	
					System.out.println("ponuda "+o.getId()+ " "+o.getSeen());
					result.add(o);
				}
				
			}
			return result;	
			
		
	}
	
	@PutMapping(path = "setSeen/{id}")
	public Offer updateResManager(@PathVariable Long id) {
		Offer o = offerService.findOne(id);
		o.setSeen("jeste");
		offerService.save(o);
		Bidder b = bidderService.findOne(o.getIdBidder());
		for(int i = 0;i<bidder.getOffers().size();i++)	{		
			if(bidder.getOffers().get(i).getId()==id){
				bidder.getOffers().get(i).setSeen("jeste");
				
				break;
			}
			
		}
		
		b.setId(b.getId());
		bidderService.save(b);
		for(int i = 0;i<bidder.getOffers().size();i++)	{		
			if(bidder.getOffers().get(i).getId()==id){
				System.out.println("sadda je " + bidder.getOffers().get(i).getSeen());
				break;
			}
			
		}
		return o;
	}	
	


}
