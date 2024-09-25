package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synex.domain.QA;

public interface QARepository extends JpaRepository<QA, Integer> {
	public List<QA> findAllByPersonAsked(String personAsked);
}
