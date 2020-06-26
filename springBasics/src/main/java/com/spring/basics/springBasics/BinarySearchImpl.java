package com.spring.basics.springBasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {	
	 //Sorting an array 
	 //Search the array
	 //Return the result
	
	@Autowired
	private SortAlgorithm sortAlgorithm;
	
	
	public int binarySearch(int[] numbers, int numberToSearchFor) {
	
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		System.out.println(sortAlgorithm);
		//Search the array 
		return 3;
	}
	
}
