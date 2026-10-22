package com.springboot.tasksystem.controller;

import com.springboot.tasksystem.dto.request.CustomerDto;
import com.springboot.tasksystem.dto.response.CustomerResponseDto;
import com.springboot.tasksystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService userService;

    @PostMapping("/api/customer/add")
    public CustomerResponseDto addCustomer(@Valid@RequestBody CustomerDto customerDto){
        return userService.addCustomer(customerDto);
    }
}
