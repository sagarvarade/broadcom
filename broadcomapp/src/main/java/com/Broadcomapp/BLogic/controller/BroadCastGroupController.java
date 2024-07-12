package com.Broadcomapp.BLogic.controller;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.repository.BroadCastGroupRepository;
import com.Broadcomapp.BLogic.service.BroadCastGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/broad-user-group")
public class BroadCastGroupController {

    @Autowired
    private BroadCastGroupService broadCastGroupService;

    @PostMapping("create")
    public BroadCastGroup createGroup(@RequestBody BroadCastGroup broadCastGroup){
        return  broadCastGroupService.saveGroup(broadCastGroup);
    }

    @GetMapping("get-by-id/{id}")
    public BroadCastGroup getGroupById(@PathVariable ("id") Long id){
        Optional<BroadCastGroup>  optionalBroadCastGroup = broadCastGroupService.findGroupById(id);
        if(optionalBroadCastGroup.isEmpty()){
            return  null;
        }else{
            return  optionalBroadCastGroup.get();
        }
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable ("id") Long id){
        try{
            broadCastGroupService.deleteGroupById(id);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not Deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-group-with-users/{id}")
    public HashMap<BroadCastGroup, List<BroadUser>> getGroupWithUsers(@PathVariable ("id") Long id){
        HashMap<BroadCastGroup, List<BroadUser>> bg = broadCastGroupService.getGroupDetails(id);
        if(bg.isEmpty()){
            return  null;
        }else{
            return  bg;
        }
    }
}
