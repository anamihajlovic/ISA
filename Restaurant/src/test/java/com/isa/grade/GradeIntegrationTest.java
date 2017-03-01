package com.isa.grade;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeIntegrationTest {

	@Autowired
	GradeRepository gradeRep;

	
	@Test
	public void checkGrade() {
		Grade grade = gradeRep.findByOrderId(6L);
		assertThat(grade.getGuestId()).isEqualTo(3);
		
		Grade grade2 = gradeRep.findByOrderId(3L);
		assertThat(grade2.getGuestId()).isEqualTo(1);
		
		Grade grade3 = gradeRep.findByOrderId(5L);
		assertThat(grade3.getGuestId()).isEqualTo(4);
	}
	
	@Test
	public void checkGradeByReservation() {
		Grade grade = gradeRep.findByGuestIdAndReservationId(1L, 2L);
		assertThat(grade.getOrderGrade()).isEqualTo(5.00);
		
		Grade grade2 = gradeRep.findByGuestIdAndReservationId(3L, 2L);
		assertThat(grade2.getOrderGrade()).isEqualTo(2.00);
		
		Grade grade3 = gradeRep.findByGuestIdAndReservationId(3L, 3L);
		assertThat(grade3.getOrderGrade()).isEqualTo(3.00);
	}
	
	
	
	@Test
	public void checkSaveGrade() {
		Grade grade = new Grade(5L, 2L, 2L, 2L, 2L, 1L, 3.00, 4.00, 4.00, new Date());		
		Grade savedGrade = gradeRep.save(grade);		
		assertThat(savedGrade.getId()).isEqualTo(5L);
	}
	
}