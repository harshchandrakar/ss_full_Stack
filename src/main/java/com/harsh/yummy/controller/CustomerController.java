package com.harsh.yummy.controller;

import com.harsh.yummy.dto.CustomerLoginRequest;
import com.harsh.yummy.dto.CustomerRequest;
import com.harsh.yummy.dto.CustomerResponse;
import com.harsh.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustoemr(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @GetMapping
    public  ResponseEntity<CustomerResponse> getCustomer(@RequestParam String email){
        return ResponseEntity.ok(customerService.getCustomer(email));
    }
    @PatchMapping
    public  ResponseEntity<String> updateCustomer(@RequestParam String email,@RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.updateCustomer(email,request));
    }

    @DeleteMapping
    public  ResponseEntity<String> deleteCustomer(@RequestParam String email){
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }
}
