package com.Broadcomapp.BLogic.controller;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.service.BroadCastGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/broad-com-app/group")
public class BroadCastGroupController {

    @Autowired
    private BroadCastGroupService broadCastGroupService;

    @PostMapping("create")
    public ResponseEntity<BroadCastGroup> createGroup(@RequestBody BroadCastGroup broadCastGroup,
                                                      @RequestHeader("user_id") String userID){
        LocalDateTime now=LocalDateTime.now();
        broadCastGroup.setCreatedBy(userID);
        broadCastGroup.setUpdatedBy(userID);
        broadCastGroup.setCreatedDate(now);
        broadCastGroup.setUpdatedDate(now);
        return  new ResponseEntity<>(broadCastGroupService.saveGroup(broadCastGroup),HttpStatus.OK);
    }

    @GetMapping("get-group-by-id/{id}")
    public ResponseEntity<BroadCastGroup> getGroupById(@PathVariable ("id") Long id,@RequestHeader("user_id") String userID){
        Optional<BroadCastGroup> optionalBroadCastGroup = broadCastGroupService.findGroupByIdAndUpdatedByUser(id,userID);
        return optionalBroadCastGroup.map(broadCastGroup -> new ResponseEntity<>(broadCastGroup, HttpStatus.OK)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("get-by-group-name/{name}")
    public ResponseEntity<BroadCastGroup> getGroupById(@PathVariable ("name") String name,@RequestHeader("user_id") String userID){
        Optional<BroadCastGroup> optionalBroadCastGroup = broadCastGroupService.findGroupByNameAndUpdatedByUser(name,userID);
        return optionalBroadCastGroup.map(broadCastGroup -> new ResponseEntity<>(broadCastGroup, HttpStatus.OK)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable ("id") Long id,@RequestHeader("user_id") String userID){
        try{
            broadCastGroupService.deleteGroupById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-group-details-by-group-name/{name}")
    public ResponseEntity<HashMap<String, List<BroadUser>>> getGroupWithUsers(@PathVariable ("name") String name,@RequestHeader("user_id") String userID){
        HashMap<String, List<BroadUser>> bg = broadCastGroupService.getGroupDetails(name,userID);
        if(bg.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return  new ResponseEntity<>(bg, HttpStatus.OK);
        }
    }
}
