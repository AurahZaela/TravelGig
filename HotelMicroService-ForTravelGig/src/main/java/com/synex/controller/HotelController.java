package com.synex.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Hotel;
import com.synex.domain.RoomType;
import com.synex.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;
	
	
	@RequestMapping(value="/searchHotel/{searchString}", method=RequestMethod.GET)
	public List<Hotel> searchHotel(@PathVariable String searchString){
		return hotelService.searchHotel(searchString);
	}
	
	@RequestMapping(value="getRoomTypesOfHotel/{hotelId}", method=RequestMethod.GET)
    public Set<RoomType> getRoomTypesOfHotel(@PathVariable int hotelId) {
        return hotelService.getRoomTypesOfHotel(hotelId);
    }
	
	@RequestMapping(value="getRoomPriceAndDiscount/{hotelId}/{roomTypeId}", method=RequestMethod.GET)
    public List<Float> getRoomPriceAndDiscount(@PathVariable int hotelId, @PathVariable int roomTypeId) {
        return hotelService.getRoomPriceAndDiscount(hotelId, roomTypeId);
    }
	
    @GetMapping("findHotelById/{id}")
    public Hotel findById(@PathVariable int id) {
        return hotelService.findById(id);
    }
	
}
