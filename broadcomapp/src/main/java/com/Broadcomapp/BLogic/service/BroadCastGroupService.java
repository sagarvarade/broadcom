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

    public HashMap<BroadCastGroup, List<BroadUser>> getGroupDetails(Long id) {
        BroadCastGroup broadCastGroup = null;
        HashMap<BroadCastGroup, List<BroadUser>> map = new HashMap<>();
        if (!broadCastGroupRepository.findById(id).isEmpty()) {
            broadCastGroup = broadCastGroupRepository.findById(id).get();
            List<BroadUser> userList = new ArrayList<>();
            for (Integer l : broadCastGroup.getBroadUsersIdList()) {
                userList.add(broadUserRepository.findById(Long.valueOf(l)).get());
            }
            System.out.println(broadCastGroup);
            System.out.println(userList);
            map.put(broadCastGroup, userList);
            return map;
        }
        return map;
    }
}
