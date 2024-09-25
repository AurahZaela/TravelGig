package com.synex.service;


import java.util.List;

import com.synex.domain.Review;

public interface ReviewService {
	 	Review save(Review review);
	    Review findById(int id);
	    List<Review> findAll();
	    void deleteById(int id);
	    boolean existById(int id);
	    
	    List<Review> findAllReviewsByHotelId(int hotelId);  // custom method
}
