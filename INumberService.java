package com.rest.service;

import java.util.List;



public interface INumberService {
	public List<Integer> getPrimeNumbers(int number);
	public boolean isPrimeNumber(int number);
}
