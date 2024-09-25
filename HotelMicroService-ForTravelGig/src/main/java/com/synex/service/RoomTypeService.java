package com.synex.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.RoomType;
import com.synex.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
    
    @Autowired RoomTypeRepository roomTypeRepository;
    

    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

 
    public RoomType findById(int id) {
        return roomTypeRepository.findById(id).orElse(null);
    }


    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }


    public void deleteById(int id) {
        roomTypeRepository.deleteById(id);
    }
    

    public boolean existById(int id) {
        return roomTypeRepository.existsById(id);
    }
    
}
