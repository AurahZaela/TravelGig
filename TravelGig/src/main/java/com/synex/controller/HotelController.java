package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.client.HotelClient;

@RestController
public class HotelController {
	
	@Autowired
	HotelClient hotelClient;
	
    @GetMapping("findHotelById/{id}")
    public JsonNode findHotelById(@PathVariable int id) {
        return hotelClient.findHotelById(id);
    }
	
	@RequestMapping(value="findHotel/{searchString}")
	public JsonNode searchHotel(@PathVariable String searchString) {
		
		return hotelClient.searchHotel(searchString);
	}
	
	    
	@RequestMapping(value="getRoomTypesOfHotel/{hotelId}")
	    public JsonNode getRoomTypesOfHotel(@PathVariable int hotelId) {
	        return hotelClient.getRoomTypesOfHotel(hotelId);
	    }
	    

    @RequestMapping(value="getRoomPriceAndDiscount/{hotelId}/{roomTypeId}")
    public JsonNode getRoomPriceAndDiscount(@PathVariable int hotelId, @PathVariable int roomTypeId) {
        return hotelClient.getRoomPriceAndDiscount(hotelId, roomTypeId);
    }
	
}
