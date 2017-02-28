package com.isa.grade;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	

}
