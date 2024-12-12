package com.arjun.EmployeeManager.EmployeeManager.service;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Address;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    Response<Address> addAddress(Address address);
    Response<Address> getAddressById(UUID code);
    Response<List<Address>> getAddresses(int page, int size);
    Response<Address> updateAddress(UUID code, Address address);
    Response<Void> deleteAddress(UUID code);
}