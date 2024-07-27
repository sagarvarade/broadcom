package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadCastGroupRepository extends JpaRepository<BroadCastGroup,Long> {

    List<BroadCastGroup> findByBroadCastGroupIdAndUpdatedBy(Long id, String userID);
    List<BroadCastGroup> findByGroupNameAndUpdatedBy(String groupName, String updatedBy);


}
