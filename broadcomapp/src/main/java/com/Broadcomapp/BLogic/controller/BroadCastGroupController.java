package com.Broadcomapp.BLogic.controller;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import com.Broadcomapp.BLogic.repository.BroadCastGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/broad-user-group")
public class BroadCastGroupController {

    @Autowired
    private BroadCastGroupRepository broadCastGroupRepository;

    @PostMapping("create")
    public BroadCastGroup createGroup(@RequestBody BroadCastGroup broadCastGroup){
        return  broadCastGroupRepository.save(broadCastGroup);
    }

    @GetMapping("get-by-id/{id}")
    public BroadCastGroup getGroupById(@PathVariable ("id") Long id){
        Optional<BroadCastGroup>  optionalBroadCastGroup = broadCastGroupRepository.findById(id);
        if(optionalBroadCastGroup.isEmpty()){
            return  null;
        }else{
            return  optionalBroadCastGroup.get();
        }
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable ("id") Long id){
        try{
            broadCastGroupRepository.deleteById(id);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not Deleted", HttpStatus.BAD_REQUEST);
        }
    }

}
