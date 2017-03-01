package com.isa.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.bill.Bill;
import com.isa.bill.BillRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillIntegrationTest {

	
	@Autowired
	BillRepository rep;
	
	@Test
	public void checkFindBill() {
		
		Bill bill = rep.findOne(1L);
		assertThat(bill.getTotalPrice()).isEqualTo(500);
		
		Bill bill2 = rep.findOne(2L);
		assertThat(bill2.getTotalPrice()).isEqualTo(4500);
		
		Bill bill3 = rep.findOne(3L);
		assertThat(bill3.getTotalPrice()).isEqualTo(1000);		
	}
	
	@Test
	public void checkSaveBill() {
		Bill newBill = new Bill(38L, "2017-01-10", "10:00:00", 2L, 1000L);
		Bill savedBill = rep.save(newBill);
		
		assertThat(savedBill.getId()).isEqualTo(newBill.getId());
		assertThat(savedBill.getBillDate()).isEqualTo(newBill.getBillDate());
		assertThat(savedBill.getTime()).isEqualTo(newBill.getTime());
	}
}
