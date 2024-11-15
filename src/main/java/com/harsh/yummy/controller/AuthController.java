package com.harsh.yummy.controller;


import com.harsh.yummy.dto.CustomerLoginRequest;
import com.harsh.yummy.dto.CustomerLoginResponse;
import com.harsh.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final CustomerService customerService;
    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponse> loginCustomer(@RequestBody @Valid CustomerLoginRequest request) {
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }
}
