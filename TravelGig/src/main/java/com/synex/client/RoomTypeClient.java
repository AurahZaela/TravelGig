package com.synex.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RoomTypeClient {
    
    public JsonNode findRoomTypeById(int id) {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/findRoomTypeById/" + id, Object.class);  // Response is List<Booking>
        Object obj = responseEntity.getBody();  
        
        ObjectMapper mapper = new ObjectMapper(); 
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class); 
        return returnObj;
    }
    
}
