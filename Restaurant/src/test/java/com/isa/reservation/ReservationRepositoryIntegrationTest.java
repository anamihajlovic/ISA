package com.isa.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.order.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryIntegrationTest {
	
	@Autowired
	ReservationRepository repository;
	
	@Test
	public void checkReservationDetails() {
		
		Reservation reservation = repository.findById(2L);
		assertThat(reservation.getGuestId()).isEqualTo(1L);
		assertThat(reservation.getResName()).isEqualTo("Lanterna");
		assertThat(reservation.getStartTime()).isEqualTo("18:30");
		assertThat(reservation.getEndTime()).isEqualTo("20:00");
		assertThat(reservation.getTables()).isEqualTo("6");

	}
	
	@Test
	public void testSaveReservation() {
		Reservation reservation = new Reservation(1L, "Lanterna", 2L, new Date(), "13:00", "14:00", "6", new ArrayList<Order>());
		
		repository.save(reservation);
		
		assertThat(reservation.getId()).isEqualTo(9L);
	}
	


}
