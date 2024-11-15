package com.harsh.yummy.service;



import com.harsh.yummy.dto.CustomerLoginRequest;
import com.harsh.yummy.dto.CustomerLoginResponse;
import com.harsh.yummy.dto.CustomerRequest;
import com.harsh.yummy.dto.CustomerResponse;
import com.harsh.yummy.entity.Customer;
import com.harsh.yummy.helper.JWTHelper;
import com.harsh.yummy.mapper.CustomerMapper;
import com.harsh.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public CustomerLoginResponse loginCustomer(CustomerLoginRequest request) {
        Optional<Customer> customer = repo.findByEmail(request.email());
        String password =customer.get().getPassword();
        if (customer.isPresent() && passwordEncoder.matches(request.password(),password)) {
            CustomerLoginResponse response = new CustomerLoginResponse(jwtHelper.generateToken(request.email()), "Login successful");
            return response;
        } else {
            CustomerLoginResponse response = new CustomerLoginResponse("", "Login Failed");
            return response;
        }
    }
    public CustomerResponse getCustomer(String request) {
        Optional<Customer> customerOPt = repo.findByEmail(request);
        Customer customer = customerOPt.orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerResponse(customer.getFirstName(),customer.getEmail());

    }

    public String updateCustomer(String email,CustomerRequest request ) {
        Optional<Customer> customerOPt = repo.findByEmail(email);
        Customer customer = customerOPt.orElseThrow(() -> new RuntimeException("Customer not found"));

        if (request.firstName() != null) {
            customer.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            customer.setLastName(request.lastName());
        }
        if (request.phoneNumber() != null) {
            customer.setPhoneNumber(request.phoneNumber());
        }

        repo.save(customer);

        return "Customer Updated Successfully";

    }
    public String deleteCustomer(String email) {
        Optional<Customer> customerOPt = repo.findByEmail(email);
        Customer customer = customerOPt.orElseThrow(() -> new RuntimeException("Customer not found"));

        repo.delete(customer);

        return "Customer Deleted Successfully";

    }
}
