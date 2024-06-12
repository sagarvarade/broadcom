package com.Broadcomapp.user.repository;

import com.Broadcomapp.user.beans.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
