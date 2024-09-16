package com.Broadcomapp.broadcast.repository;

import com.Broadcomapp.broadcast.beans.BroadCastGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BroadCastGroupRepository extends JpaRepository<BroadCastGroup,Long> {

    Optional<BroadCastGroup> findByBroadCastGroupIdAndUpdatedBy(Long id, String userID);

    Optional<BroadCastGroup> findByGroupNameAndUpdatedBy(String groupName, String updatedBy);
}
