package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.BroadCastGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadCastGroupRepository extends JpaRepository<BroadCastGroup,Long> {
}
