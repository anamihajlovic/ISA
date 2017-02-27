package com.isa.bill;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bills")
public class BillController {

	private HttpSession httpSession;
	private final BillService billService;
	
	@Autowired
	public BillController(HttpSession httpSession, final BillService billService) {		
		this.httpSession = httpSession;
		this.billService = billService;
	}
	
	@PostMapping(path = "/createBill")
	public String createBill(@RequestBody Bill bill) {
		
		try{
			billService.save(bill);
			return "success";
		}catch(Exception e) {
			return "failure";
		}
		
		
	}
	
}
