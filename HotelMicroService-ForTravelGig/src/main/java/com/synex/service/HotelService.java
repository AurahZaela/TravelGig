package com.synex.service;

import java.util.HashSet;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Hotel;
import com.synex.domain.HotelRoom;
import com.synex.domain.RoomType;
import com.synex.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	public List<Hotel> searchHotel(String searchSstring){
		return hotelRepository.findByHotelNameLikeOrAddressLikeOrCityLike("%"+searchSstring+"%", "%"+searchSstring+"%", "%"+searchSstring+"%");
	}
	
	public Set<RoomType> getRoomTypesOfHotel(int hotelId) {
        Set<RoomType> roomTypesOfHotel = new HashSet<>();
        
        Set<HotelRoom> hotelRooms = hotelRepository.findById(hotelId).orElse(null).getHotelRooms();  
        for (HotelRoom room : hotelRooms) {
            roomTypesOfHotel.add(room.getType());
        }
        return roomTypesOfHotel;
    }
	
	public List<Float> getRoomPriceAndDiscount(int hotelId, int roomTypeId) {
        
        List<Float> priceDiscountRoomId = new ArrayList<>();

        Set<HotelRoom> hotelRooms = hotelRepository.findById(hotelId).orElse(null).getHotelRooms();  
        for (HotelRoom room : hotelRooms) {
            if (room.getType().getTypeId() == roomTypeId) {  
                priceDiscountRoomId.add(room.getPrice());   
                priceDiscountRoomId.add(room.getDiscount());
                priceDiscountRoomId.add((float) room.getHotelRoomId());
                break;  
            }
        }
        return priceDiscountRoomId;
    }
	
    public Hotel findById(int id) {
        return hotelRepository.findById(id).orElse(null);
    }
	
}
