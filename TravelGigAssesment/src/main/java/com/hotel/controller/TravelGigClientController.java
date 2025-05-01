package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotel.component.TravelGigClientComponent;


//on browser: http://localhost:8282/hotel.html
@RestController
public class TravelGigClientController {
	
	@Autowired
	TravelGigClientComponent travelGigApiClient;
	
	@RequestMapping(value = "/travelGig/addHotel",method = RequestMethod.POST)
	public JsonNode addHotel(@RequestBody JsonNode node) {
		return travelGigApiClient.hotelPostClient(node);
		
		
	}
	
}
