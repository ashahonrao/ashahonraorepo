package com.rest.handler;

import java.util.ArrayList;
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.rest.model.PrimeEntity;
import com.rest.model.PrimeNumber;
import com.rest.service.INumberService;

import io.swagger.annotations.ApiParam;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiParam;


@Validated
@RestController
public class NumberController {
 
	@Autowired
	private INumberService numberService;  
	
	@GetMapping(value = "/isPrime/{number}") 
	public ResponseEntity<PrimeNumber> isPrimeNumber(
			 @ApiParam(
			            value = "A given number is prime or not" ,
			            example ="{number}")
			@PathVariable("number") @Min(2)  int number)   
	{  
	//finds all the products 
		PrimeNumber primeNumber = new PrimeNumber();
		primeNumber.setInitial(number);
		primeNumber.setPrime(numberService.isPrimeNumber(number));
	
		return new ResponseEntity<PrimeNumber>(primeNumber, HttpStatus.OK);	
	
	}  
	
	//mapping the getPrimes() method to /primes/{number}
	@GetMapping(value = "/primes/{number}")  
	public ResponseEntity<PrimeEntity> getPrimes(
			 @ApiParam(
			            value = "A given number upto which prime numbers to be found" ,
			            example ="{number}")
			@PathVariable("number") @Min(2)  int number)   
	{  
	//finds all the products 
		PrimeEntity primeEntity = new PrimeEntity();
		primeEntity.setInitial(number);
		primeEntity.setPrimes(new ArrayList<Integer>());
		
	List<Integer> primeList = numberService.getPrimeNumbers(number); 
	
	//returns the primer number list
	if(primeList != null && !primeList.isEmpty())
		primeEntity.setPrimes(primeList);
	
	return new ResponseEntity<PrimeEntity>(primeEntity, HttpStatus.OK);	
	
	}  
	
	@ExceptionHandler(ConstraintViolationException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(NumberFormatException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<String> handleConstraintViolationException(NumberFormatException e) {
	    return new ResponseEntity<>("not valid number: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
}  

