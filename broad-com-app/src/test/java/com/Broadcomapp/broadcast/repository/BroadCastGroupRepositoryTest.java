package com.Broadcomapp.broadcast.repository;

import com.Broadcomapp.broadcast.beans.BroadCastGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@SpringBootTest
class BroadCastGroupRepositoryTest {

    @Autowired
    private BroadUserRepository broadUserRepository;

    @Autowired
    private BroadCastGroupRepository broadCastGroupRepository;
  //  @Test
    public void saveGroup(){
        BroadCastGroup brGroup=BroadCastGroup.builder().groupName("Broup_1").broadUsersIdList(List.of(1,2,3)).build();
        broadCastGroupRepository.save(brGroup);
    }



}