package com.synex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Guest;
import com.synex.repository.GuestRepository;



@Service
public class GuestServiceImpl implements GuestService {

@Autowired GuestRepository guestRepository;
    
    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }
    
    @Override
    public Guest findById(int id) {
        return guestRepository.findById(id).orElse(null);
    }
    
    @Override
    public boolean existById(int id) {
        return guestRepository.existsById(id);
    }

    @Override  // Not using it right now. Instead use a loop to save a guest one by one from jsp.
    public boolean saveAllGuestsFromGuestList(List<Guest> guestList) {
        try {
            for (Guest guest : guestList) {
                    guestRepository.save(guest);
            }
        } catch (Exception e) {
            System.out.println("Exception on saveAllGuestsFromGuestList");
            return false;
        }
        return true;
    }

}
