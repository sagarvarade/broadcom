package com.Broadcomapp.broadcast.repository;

import com.Broadcomapp.broadcast.beans.BroadUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BroadUserRepository extends JpaRepository<BroadUser, Long> {
    Optional<BroadUser> findByBroadUserIdAndUpdatedBy(Long id, String user);

    List<BroadUser> findAllByUpdatedBy(String user);
    @Modifying
    @Transactional
    @Query("delete from BroadUser where broadUserId=:id and updatedBy=:user")
    void deleteByBroadUserIdAndUpdatedBy(Long id,String user);
}
