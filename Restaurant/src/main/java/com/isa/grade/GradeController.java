package com.isa.grade;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradeController {
	
	private HttpSession httpSession;
	private final GradeService gradeService;
	
	@Autowired
	public GradeController(HttpSession httpSession, final GradeService gradeService) {
		super();
		this.httpSession = httpSession;
		this.gradeService = gradeService;
	}
	
	@PostMapping(path = "/addNew")
	public Grade addNewGrade(@RequestBody Grade grade) {
		
		System.out.println(grade.getGuestId());
		System.out.println(grade.getOrderId());
		System.out.println(grade.getRestaurantId());
		System.out.println(grade.getWaiterId());
		
		System.out.println(grade.getWaiterGrade());
		System.out.println(grade.getOrderGrade());
		System.out.println(grade.getRestaurantGrade());
		
		try {
			gradeService.save(grade);
			return grade;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
							
	}
	
	
	@GetMapping(path = "/getRatedReservation/{reservationId}/{guestId}")
	public Grade getRatedReservation(@PathVariable("reservationId") Long reservationId, @PathVariable("guestId") Long guestId) {
		Grade grade = gradeService.findByGuestIdAndReservationId(guestId, reservationId);
		return grade;
	}
	
	

}
