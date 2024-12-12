package com.arjun.EmployeeManager.EmployeeManager.service.impl;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Address;
import com.arjun.EmployeeManager.EmployeeManager.repo.AddressRepo;
import com.arjun.EmployeeManager.EmployeeManager.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public Response<Address> addAddress(Address address) {
        try {
            Address savedAddress = addressRepo.save(address);
            return new Response<>(true, "Address added successfully", savedAddress);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add address");
        }
    }

    @Override
    public Response<Address> getAddressById(UUID code) {
        Optional<Address> address = addressRepo.findById(code);
        if (address.isPresent()) {
            return new Response<>(true, "Address fetched successfully", address.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
        }
    }

    @Override
    public Response<List<Address>> getAddresses(int page, int size) {
        Page<Address> addressPage = addressRepo.findAll(PageRequest.of(page, size));
        List<Address> addresses = addressPage.getContent();
        return new Response<>(true, "Paginated addresses fetched successfully", addresses);
    }

    @Override
    public Response<Address> updateAddress(UUID code, Address address) {
        if (addressRepo.existsById(code)) {
            address.setCode(code);
            Address updatedAddress = addressRepo.save(address);
            return new Response<>(true, "Address updated successfully", updatedAddress);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
        }
    }

    @Override
    public Response<Void> deleteAddress(UUID code) {
        if (addressRepo.existsById(code)) {
            addressRepo.deleteById(code);
            return new Response<>(true, "Address deleted successfully", null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
        }
    }
}