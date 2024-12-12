package com.arjun.EmployeeManager.EmployeeManager.controller;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Address;
import com.arjun.EmployeeManager.EmployeeManager.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Response<Address>> addAddress(@Valid @RequestBody Address address) {
        return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Address>> getAddressById(@PathVariable UUID code) {
        return ResponseEntity.ok(addressService.getAddressById(code));
    }

    @GetMapping
    public ResponseEntity<Response<List<Address>>> getAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(addressService.getAddresses(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Address>> updateAddress(
            @PathVariable UUID code, @Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.updateAddress(code, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteAddress(@PathVariable UUID code) {
        return ResponseEntity.ok(addressService.deleteAddress(code));
    }
}
