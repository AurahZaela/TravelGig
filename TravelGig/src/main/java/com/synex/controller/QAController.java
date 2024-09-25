package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.client.QAClient;

@RestController
public class QAController {

	@Autowired
	QAClient qaClient;
	
	@PostMapping("saveQA")
	public JsonNode saveQA(@RequestBody JsonNode json) {
		return qaClient.saveQA(json);
	}
	
	@GetMapping("findAllQs")
	public JsonNode getAllQ() {
		return qaClient.findAllQs();
	}
	
	@PostMapping("updateQ")
	public JsonNode updateQA(@RequestBody JsonNode json) {
		return qaClient.updateQ(json);
	}
	
	@GetMapping("findAllQsByUsername/{userName}")
	public JsonNode getAllByUsername(@PathVariable String userName) {
		return qaClient.findAllByUserName(userName);
	}
	
}
