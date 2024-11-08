package com.harsh.yummy.service;



import com.harsh.yummy.dto.CustomerLoginRequest;
import com.harsh.yummy.dto.CustomerRequest;
import com.harsh.yummy.entity.Customer;
import com.harsh.yummy.helper.JWTHelper;
import com.harsh.yummy.mapper.CustomerMapper;
import com.harsh.yummy.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final  JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
    public String loginCustomer(CustomerLoginRequest request) {
        Optional<Customer> customer = repo.findByEmail(request.email());
        String password =customer.get().getPassword();
        if (customer.isPresent() && passwordEncoder.matches(request.password(),password)) {

            return jwtHelper.generateToken(request.email());
        } else {
            return "Customer not found";
        }
    }
}
