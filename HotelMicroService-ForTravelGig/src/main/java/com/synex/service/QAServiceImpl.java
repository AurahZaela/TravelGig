package com.synex.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.QA;
import com.synex.repository.QARepository;

@Service
public class QAServiceImpl implements QAService {

	@Autowired
    QARepository qaRepository;
	

	@Override
	public QA saveQA(QA qa) {
		 qa.setStatus("Unanswered");
		 qa.setAnswer("N/a");
		 return qaRepository.save(qa);
	}

	@Override
	public QA answerQA(int id, String answer) {
		 Optional<QA> oqa = qaRepository.findById(id);
	        if(!oqa.isPresent()){
	            return null;
	        }
	        QA qa = oqa.get();
	        qa.setAnswer(answer);
	        qa.setStatus("Answered");
	        return qaRepository.save(qa);
	}
	
	@Override
	public List<QA> getAllQs(){
		return qaRepository.findAll();
	}

	@Override
	public QA updateQ(QA qa) {
		qa.setStatus("Answered");
		return qaRepository.save(qa);
	}

	@Override
	public List<QA> findAllByUserName(String personAsked) {
		return qaRepository.findAllByPersonAsked(personAsked);
	}

}
