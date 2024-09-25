package com.synex.service;

import java.util.List;

import com.synex.domain.QA;

public interface QAService {
	
    public QA saveQA(QA qa);
    public QA answerQA(int id, String answer);
    public List<QA> getAllQs();
    public QA updateQ(QA qa);
    
    public List<QA> findAllByUserName(String personAsked);
}
