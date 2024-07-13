package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.BroadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadUserRepository extends JpaRepository<BroadUser, Long> {


}
