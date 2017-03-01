package com.isa.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderIntegrationTests {

	@Autowired
	OrderRepository orderRep;
	
	
	@Test
	public void checkOrderStatus() {
		Order order = orderRep.findOne(1L);
		assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ordered);
		
		Order order2 = orderRep.findOne(2L);
		assertThat(order2.getOrderStatus()).isEqualTo(OrderStatus.ordered);
		
		Order order3 = orderRep.findOne(3L);
		assertThat(order3.getOrderStatus()).isEqualTo(OrderStatus.ordered);		
	}

	
}
