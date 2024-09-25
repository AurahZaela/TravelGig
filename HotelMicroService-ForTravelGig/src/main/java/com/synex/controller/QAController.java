package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.QA;
import com.synex.service.QAService;

@RestController
public class QAController {
	
	
		@Autowired
	    QAService qaService;

		@PostMapping("saveQA")
		public QA save(@RequestBody QA questionAnswer) {
			return qaService.saveQA(questionAnswer);
		}
		
		@GetMapping("findAllQs")
		public List<QA> getAllQs(){
			return qaService.getAllQs();
		}

		@PostMapping("updateQA")
		public QA update(@RequestBody QA qa) {
			return qaService.updateQ(qa);
		}
		
		@GetMapping("findAllQsByUsername/{personAsked}")
		public List<QA> findAllQByUsername(@PathVariable String personAsked){
			return qaService.findAllByUserName(personAsked);
		}
		
}
