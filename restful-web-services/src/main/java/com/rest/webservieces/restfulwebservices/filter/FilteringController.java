package com.rest.webservieces.restfulwebservices.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {

		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		// only send field1 and field2

		Set<String> properties = new HashSet<String>();
		properties.add("field1");
		properties.add("field2");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);

		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter",
				SimpleBeanPropertyFilter.filterOutAllExcept(properties));

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListSomeBean() {
		// field2 and field3
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
		Set<String> properties = new HashSet<String>();
		properties.add("field2");
		properties.add("field3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);

		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter",
				SimpleBeanPropertyFilter.filterOutAllExcept(properties));

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);

		return mapping;
	}
}
