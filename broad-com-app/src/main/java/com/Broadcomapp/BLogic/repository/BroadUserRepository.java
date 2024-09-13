package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.BroadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BroadUserRepository extends JpaRepository<BroadUser, Long> {
    Optional<BroadUser> findByBroadUserIdAndUpdatedBy(Long id, String user);

    List<BroadUser> findAllByUpdatedBy(String user);
    @Modifying
    @Query("delete from BroadUser where id = :id and updatedBy=:user")
    void deleteByBroadUserIdAndUpdatedBy(@Param("id") Long id, @Param("user") String user);
}
