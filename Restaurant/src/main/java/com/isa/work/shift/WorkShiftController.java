package com.isa.work.shift;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.cook.Cook;
import com.isa.restaurant.Restaurant;
import com.isa.work.day.WorkDay;

@RestController
@RequestMapping("/workShifts")
public class WorkShiftController {
	
	private HttpSession httpSession;
	private final WorkShiftService workShiftService;		
	
	@Autowired
	public WorkShiftController(final HttpSession httpSession, final WorkShiftService workShiftService) {		
		this.httpSession = httpSession;
		this.workShiftService = workShiftService;
	}

	@GetMapping(path = "/getWorkShift/{id}")
	public WorkShift getWorkShift(@PathVariable Long id) {		
		return workShiftService.findOne(id);
	}
	
	
	
	
}
