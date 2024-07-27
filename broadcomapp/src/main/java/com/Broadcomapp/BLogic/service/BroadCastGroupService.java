package com.Broadcomapp.BLogic.service;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.repository.BroadCastGroupRepository;
import com.Broadcomapp.BLogic.repository.BroadUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BroadCastGroupService {

    @Autowired
    private BroadCastGroupRepository broadCastGroupRepository;

    @Autowired
    private BroadUserRepository broadUserRepository;

    public BroadCastGroup saveGroup(BroadCastGroup broadCastGroup) {
        return broadCastGroupRepository.save(broadCastGroup);
    }

    public Optional<BroadCastGroup> findGroupById(Long id) {
        return broadCastGroupRepository.findById(id);
    }

    public void deleteGroupById(Long id) {
        broadCastGroupRepository.deleteById(id);
    }

    public HashMap<String, List<BroadUser>> getGroupDetails(String name,String user) {
        BroadCastGroup broadCastGroup = null;
        HashMap<String, List<BroadUser>> map = new HashMap<>();
        Optional<BroadCastGroup>  brGroup= broadCastGroupRepository.findByGroupNameAndUpdatedBy(name,user);
        if (brGroup.isPresent()) {
            broadCastGroup = brGroup.get();
            List<BroadUser> userList = new ArrayList<>();
            for (Integer l : broadCastGroup.getBroadUsersIdList()) {
                userList.add(broadUserRepository.findById(Long.valueOf(l)).get());
            }
            System.out.println(broadCastGroup);
            System.out.println(userList);
            map.put(broadCastGroup.getGroupName(), userList);
            return map;
        }
        return map;
    }

    public Optional<BroadCastGroup> findGroupByNameAndUpdatedByUser(String name, String userID) {
        return broadCastGroupRepository.findByGroupNameAndUpdatedBy(name,userID);
    }

    public Optional<BroadCastGroup> findGroupByIdAndUpdatedByUser(Long id, String userID) {
        return broadCastGroupRepository.findByBroadCastGroupIdAndUpdatedBy(id,userID);
    }
}
