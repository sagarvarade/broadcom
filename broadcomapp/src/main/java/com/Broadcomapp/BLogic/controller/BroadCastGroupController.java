package com.Broadcomapp.BLogic.controller;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.repository.BroadCastGroupRepository;
import com.Broadcomapp.BLogic.service.BroadCastGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/broad-user-group")
public class BroadCastGroupController {

    @Autowired
    private BroadCastGroupService broadCastGroupService;

    @PostMapping("create")
    public BroadCastGroup createGroup(@RequestBody BroadCastGroup broadCastGroup,@RequestHeader("user_id") String userID){
        LocalDateTime now=LocalDateTime.now();
        broadCastGroup.setCreatedBy(userID);
        broadCastGroup.setUpdatedBy(userID);
        broadCastGroup.setCreatedDate(now);
        broadCastGroup.setUpdatedDate(now);
        return  broadCastGroupService.saveGroup(broadCastGroup);
    }

    @GetMapping("get-by-id/{id}")
    public List<BroadCastGroup> getGroupById(@PathVariable ("id") Long id,@RequestHeader("user_id") String userID){
        List<BroadCastGroup>  optionalBroadCastGroup = broadCastGroupService.findGroupByIdAndUpdatedByUser(id,userID);
        if(optionalBroadCastGroup.isEmpty()){
            return  List.of();
        }else{
            return  optionalBroadCastGroup;
        }
    }

    @GetMapping("get-by-group-name/{name}")
    public List<BroadCastGroup> getGroupById(@PathVariable ("name") String name,@RequestHeader("user_id") String userID){
        List<BroadCastGroup>  optionalBroadCastGroup = broadCastGroupService.findGroupByNameAndUpdatedByUser(name,userID);
        if(optionalBroadCastGroup.isEmpty()){
            return  List.of();
        }else{
            return  optionalBroadCastGroup;
        }
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable ("id") Long id,@RequestHeader("user_id") String userID){
        try{
            broadCastGroupService.deleteGroupById(id);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Not Deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-group-with-users/{id}")
    public HashMap<BroadCastGroup, List<BroadUser>> getGroupWithUsers(@PathVariable ("id") Long id,@RequestHeader("user_id") String userID){
        HashMap<BroadCastGroup, List<BroadUser>> bg = broadCastGroupService.getGroupDetails(id);
        if(bg.isEmpty()){
            return  null;
        }else{
            return  bg;
        }
    }
}
