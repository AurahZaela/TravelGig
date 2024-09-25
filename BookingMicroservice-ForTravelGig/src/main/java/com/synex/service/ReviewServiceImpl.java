package com.synex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Review;
import com.synex.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	 @Autowired 
	 ReviewRepository reviewRepository;
	    
	    @Override
	    public Review save(Review review) {
	        return reviewRepository.save(review);
	    }

	    @Override
	    public Review findById(int id) {
	        return reviewRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<Review> findAll() {
	        return reviewRepository.findAll();
	    }

	    @Override
	    public void deleteById(int id) {
	        reviewRepository.deleteById(id);
	    }
	    
	    @Override
	    public boolean existById(int id) {
	        return reviewRepository.existsById(id);
	    }

	    @Override
	    public List<Review> findAllReviewsByHotelId(int hotelId) {
	        List<Review> filteredList = reviewRepository.findAll().stream()
	                .filter(r -> r.getBooking().getHotelId() == hotelId)
	                .collect(Collectors.toList());
	        return filteredList;
	    }
}
