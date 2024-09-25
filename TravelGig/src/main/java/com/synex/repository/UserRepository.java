package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.synex.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	@Query(value = "SELECT userId FROM users ORDER BY userId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();

}
