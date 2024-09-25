package com.synex.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Review;
import com.synex.service.ReviewService;

@RestController
public class ReviewController {
    
    @Autowired ReviewService reviewService;
    
    @PostMapping("saveReview")
    public Review save(@RequestBody Review review) {
        return reviewService.save(review);
    }
    
    @GetMapping("findReviewById/{id}")
    public Review findById(@PathVariable int id) {
        return reviewService.findById(id);
    }
    
    @GetMapping("findAllReviews")
    public List<Review> findAll() {
        return reviewService.findAll();
    }
    
    @GetMapping("existReviewById/{id}")
    public boolean existById(@PathVariable int id) {
        return reviewService.existById(id);
    }
    
    @DeleteMapping("deleteReviewById/{id}")
    public void deleteById(@PathVariable int id) {
        reviewService.deleteById(id);
    }
    
    @GetMapping("findAllReviewsByHotelId/{hotelId}")
    public List<Review> findAllReviewsByHotelId(@PathVariable int hotelId) {
        return reviewService.findAllReviewsByHotelId(hotelId);
    }
    
}
