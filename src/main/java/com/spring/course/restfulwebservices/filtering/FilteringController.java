package com.spring.course.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	// field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeans() {
		List<SomeBean> someBeans = new ArrayList<>();
		
		someBeans.add(new SomeBean("value1", "value2", "value3"));
		someBeans.add(new SomeBean("value4", "value5", "value6"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
}
