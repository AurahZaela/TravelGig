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
public class QAClient {
	
	public JsonNode saveQA(JsonNode json) {  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON);  
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);  
        
        RestTemplate restTemplate = new RestTemplate();  
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8383/saveQA", request, Object.class);
        Object obj = responseEntity.getBody();  
        
        ObjectMapper mapper = new ObjectMapper();  
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);
        return returnObj;
        
    }
	
	public JsonNode findAllQs() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/findAllQs", Object.class);  // Response is List<Booking>
        Object obj = responseEntity.getBody();  // get body of responseEntity
        
        ObjectMapper mapper = new ObjectMapper();  // let mapper to convert it to json
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);  // JsonNode can represent any json.
        return returnObj;
    }
	
	public JsonNode updateQ(JsonNode json) {  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON);  
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);  
        
        RestTemplate restTemplate = new RestTemplate();  
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8383/updateQA", request, Object.class);
        Object obj = responseEntity.getBody();  
        
        ObjectMapper mapper = new ObjectMapper();  
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class);
        return returnObj;
        
    }
	
	
	public JsonNode findAllByUserName(String userName) {
  
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/findAllQsByUsername/" + userName, Object.class);  
        Object obj = responseEntity.getBody();  
        ObjectMapper mapper = new ObjectMapper(); 
        JsonNode returnObj = mapper.convertValue(obj, JsonNode.class); 
        return returnObj;
    }
	
	
}
