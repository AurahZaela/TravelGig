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
public class BookingClient {
    
	 public JsonNode saveBooking(JsonNode json) {  
	        HttpHeaders headers = new HttpHeaders();  
	        headers.setContentType(MediaType.APPLICATION_JSON);  
	        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers); 
	        
	        RestTemplate restTemplate = new RestTemplate();  
	        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/saveBooking", request, Object.class);
	        Object obj = responseEntity.getBody();  // get body of response
	        
	        ObjectMapper mapper = new ObjectMapper();  // use objectMapper to convert body to json.
	        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);
	        return returnObj;
	    }
	    
	    public JsonNode findAllByUserName(String userName) {
	        // RestTemplate can make requests to another project on another port.
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/findAllByUserName/" + userName, Object.class); 
	        Object obj = responseEntity.getBody();  
	        ObjectMapper mapper = new ObjectMapper();  
	        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);  
	        return returnObj;
	    }
	    
	    public void deleteBookingById(int id) {
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete("http://localhost:8484/deleteBookingById/" + id, Object.class);
	    }
	    
	    public void cancelBookingById(int id) {
	        // RestTemplate can make requests to another project on another port.
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete("http://localhost:8484/cancelBookingById/" + id, Object.class);
	    }
    
}
