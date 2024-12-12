package com.arjun.EmployeeManager.EmployeeManager.repo;

import com.arjun.EmployeeManager.EmployeeManager.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address, UUID> {

}
