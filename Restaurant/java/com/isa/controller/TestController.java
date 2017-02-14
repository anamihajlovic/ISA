package com.isa.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.model.Test;

@RestController
@RequestMapping("/test")
public class TestController {
	

	public TestController() {}
	
	@PostMapping("/{id}")
	public void testF(@PathVariable Long id, @RequestBody Test test) {
		System.out.println(test.getName() + " " + test.getPass()+ " , "+id);
	}

}
