package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synex.component.CreateFile;
import com.synex.domain.Booking;
import com.synex.service.BookingService;
import com.synex.service.EmailService;

@RestController
public class BookingController {
	
	   @Autowired 
	   BookingService bookingService;
	   
	   @Autowired
	   EmailService emailService;
	   
	   @Autowired
	   CreateFile createFile;
	   
	   public void sendEmailHelper(Booking booking,String message, String pdfPath, String filename) {
		   	
		   emailService.sentEmail(booking.getUserEmail(), "Hotel Reservation Comfirmation", message, pdfPath, filename);
	   }
	    
	    @PostMapping("saveBooking")
	    public Booking save(@RequestBody Booking booking) {
	        bookingService.save(booking);
	        
	        String filename = "Reservation_"+booking.getHotelId()+".pdf";
	        String message = createFile.messageForm(booking);
//	        System.out.println(message);
	        String pdfPath = createFile.PDF(message, "/src/main/resources/pdf/", filename);

	        sendEmailHelper(booking,message,pdfPath,filename);
	        return booking;
	    }
	    
	    @GetMapping("findBookingById/{id}")
	    public Booking findById(@PathVariable int id) {
	        return bookingService.findById(id);
	    }
	    
	    @DeleteMapping("deleteBookingById/{id}")
	    public void deleteById(@PathVariable int id) {
	        bookingService.deleteById(id);
	    }
	    
	    @GetMapping("existBookingById/{id}")
	    public boolean existById(@PathVariable int id) {
	        return bookingService.existById(id);
	    }
	    
	    @GetMapping("findAllByUserName/{userName}")
	    public List<Booking> findAllByUserName(@PathVariable String userName) {
	        return bookingService.findAllByUserName(userName);
	    }

	    @DeleteMapping("cancelBookingById/{id}")  
	    public void cancelBookingById(@PathVariable int id) {
	        bookingService.cancelBookingById(id);
	    }
	    
}
