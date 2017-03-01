package com.isa.employees;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.employed.ClothesSize;
import com.isa.employed.ShoesSize;
import com.isa.user.Role;
import com.isa.waiter.Waiter;
import com.isa.waiter.WaiterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterIntegrationTest {

	@Autowired
	WaiterRepository repository;
	
	
	
	@Test
	public void checkWaiter() {
		
		Waiter waiter = repository.findByEmail("waiter2@gmail.com");
		assertThat(waiter.getFirstName()).isEqualTo("waiter2");
		
		Waiter waiter2 = repository.findByEmail("waiter1@gmail.com");
		assertThat(waiter2.getFirstName()).isEqualTo("waiter1");
	}
	
	@Test
	public void getByEmailAndPassword() {		
		Waiter waiter = repository.findByEmailAndPassword("waiter2@gmail.com", "waiter2");
		assertThat(waiter.getFirstName()).isEqualTo("waiter2");
		assertThat(waiter.getFirstName()).isNotEqualTo("waiter1");
	}
	
	@Test
	public void checkSaveWaiter() {
		Waiter waiter = new Waiter(6L);		
		waiter.setEmail("waiter6@gmail.com");
		waiter.setPassword("waiter6");
		waiter.setFirstLogIn(true);
		waiter.setFirstName("Konobar");
		waiter.setLastName("Konobaric");
		waiter.setUserRole(Role.waiter);		
		waiter.setRestaurantId(2L);
		waiter.setBirthday(new Date());
		waiter.setClothesSize(ClothesSize.M);
		waiter.setShoesSize(ShoesSize.no39);
				
		Waiter savedWaiter = repository.save(waiter);
		assertThat(savedWaiter.getId()).isEqualTo(6L);
	}
	
}
