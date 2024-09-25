package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class QA {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String question;

	    private String answer;

	    private String status;

	    private String personAsked;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getPersonAsked() {
			return personAsked;
		}
		public void setPersonAsked(String personAsked) {
			this.personAsked = personAsked;
		}


}
