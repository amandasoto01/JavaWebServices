package com.spring.basics.springBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {
	
	//what are beans?
	//what are the dependencies of a bean?
	//where to search for beans? => no need
	
	
	public static void main(String[] args) {
		
		//BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new QuickSortAlgorithm());
		
		//Application Context maintain all the beans
		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		int result = binarySearch.binarySearch(new int[] {12, 4,6 }, 3);
		System.out.println(result);
	}

}
