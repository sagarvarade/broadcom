package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BroadCastGroupRepository extends JpaRepository<BroadCastGroup,Long> {

    Optional<BroadCastGroup> findByBroadCastGroupIdAndUpdatedBy(Long id, String userID);

    Optional<BroadCastGroup> findByGroupNameAndUpdatedBy(String groupName, String updatedBy);
}
