 package com.synex.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelClient {
	
	private static final String hotelMicroservice = "http://localhost:8383/searchHotel/";
	private static final String hotelTypeService = "http://localhost:8383/getRoomTypesOfHotel/";
	private static final String hotelPriceAndDiscount = "http://localhost:8383/getRoomPriceAndDiscount/";
	
	
	
	
    public JsonNode findHotelById(int id) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/findHotelById/" + id, Object.class);  // Response is List<Booking>
        Object obj = responseEntity.getBody();  
        
        ObjectMapper mapper = new ObjectMapper(); 
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class); 
        return returnObj;
    }
	

	public JsonNode searchHotel(String searchString) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(hotelMicroservice + searchString, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects,  JsonNode.class);
		
		return returnObj;
	}
	
	 public JsonNode getRoomTypesOfHotel(int hotelId) {
		 	HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(hotelTypeService + hotelId, Object.class);  
	        Object obj = responseEntity.getBody(); 
	        
	        ObjectMapper mapper = new ObjectMapper(); 
	        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);
	        return returnObj;
	    }
	    
	    public JsonNode getRoomPriceAndDiscount(int hotelId, int roomTypeId) {
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(hotelPriceAndDiscount + hotelId + "/" + roomTypeId, Object.class);  
	        Object obj = responseEntity.getBody(); 
	        
	        ObjectMapper mapper = new ObjectMapper();  
	        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);  
	        return returnObj;
	    }

}
