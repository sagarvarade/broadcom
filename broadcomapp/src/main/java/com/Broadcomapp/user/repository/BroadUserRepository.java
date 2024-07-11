package com.Broadcomapp.user.repository;

import com.Broadcomapp.user.beans.BroadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadUserRepository extends JpaRepository<BroadUser, Long> {


}
