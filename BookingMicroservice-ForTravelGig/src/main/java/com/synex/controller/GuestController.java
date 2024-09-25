package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Guest;
import com.synex.service.GuestService;

@RestController
public class GuestController {
	
@Autowired 
GuestService guestService;
    
    @PostMapping("saveGuest")
    public Guest save(@RequestBody Guest guest) {
        return guestService.save(guest);
    }
    
    @GetMapping("findGuestById/{id}")
    public Guest findById(@PathVariable int id) {
        return guestService.findById(id);
    }
    
    @GetMapping("existGuestById/{id}")
    public boolean existById(@PathVariable int id) {
        return guestService.existById(id);
    }
    
    @PostMapping("saveAllGuestsFromGuestList")
    public boolean saveAllGuestsFromGuestList(@RequestBody List<Guest> guestList) {
        return guestService.saveAllGuestsFromGuestList(guestList);
    }
    
}
