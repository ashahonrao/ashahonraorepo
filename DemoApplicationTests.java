package com.rest.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.service.INumberService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	INumberService numberService;
	

//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void testgetPrimes_integrated() {
		NumberControllerTest test = new NumberControllerTest();
		test.testgetPrimes();		
	}
	
	@Test
	public void testgetPrimes() {
		
		List<Integer> primeList = numberService.getPrimeNumbers(1);
		assertNotNull(primeList);	
	
		
	}

	@Test
	public void testgetPrimes1() {
		
		List<Integer> primeList = numberService.getPrimeNumbers(10);
		assertTrue(primeList.size()> 0);		
		
	}	
	

	
}
