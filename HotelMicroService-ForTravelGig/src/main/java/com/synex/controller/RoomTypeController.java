package com.synex.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.RoomType;
import com.synex.service.RoomTypeService;

@RestController
public class RoomTypeController {
    
    @Autowired 
    RoomTypeService roomTypeService;
    
    @PostMapping("saveRoomType")
    public RoomType save(@RequestBody RoomType roomType) {
        return roomTypeService.save(roomType);
    }
    
    @GetMapping("findRoomTypeById/{id}")
    public RoomType findById(@PathVariable int id) {
        return roomTypeService.findById(id);
    }
    
    @GetMapping("findAllRoompTypes")
    public List<RoomType> findAll() {
        return roomTypeService.findAll();
    }
    
    @GetMapping("existRoomTypeById/{id}")
    public boolean existById(@PathVariable int id) {
        return roomTypeService.existById(id);
    }
    
    @DeleteMapping("deleteRoomTypeById/{id}")
    public void deleteById(@PathVariable int id) {
        roomTypeService.deleteById(id);
    }
    
}
