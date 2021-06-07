package com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements INumberService{

	@Override
	public List<Integer> getPrimeNumbers(int number) {
		// TODO Auto-generated method stub
		 List<Integer> primes = new ArrayList<Integer>();
		 
				 primes = IntStream.rangeClosed(2, number)
                 .filter(n -> isPrime(n))
                 .boxed()
                 .collect(Collectors.toList());
		 
		return primes;
	}
	
	@Override
	public boolean isPrimeNumber(int number) {
		// TODO Auto-generated method stub		 
			return isPrime(number);
	}
	
	private boolean isPrime(int number) {
		
		if(number <=2) {
			return number == 2;
		}
		else {
			return ( number % 2 != 0 &&
					IntStream.rangeClosed(3, (int) Math.sqrt(number))
                    .filter(n -> n % 2 != 0)
                    .noneMatch(n -> (number % n == 0)));
					
					
					
		}
	}

}
