package com.synex.service;

import java.util.List;

import com.synex.domain.Guest;

public interface GuestService {
	 	public Guest save(Guest guest);
	    public Guest findById(int id);
	    boolean existById(int id);
	    
	    public boolean saveAllGuestsFromGuestList(List<Guest> guestList);
}
