package com.springboot.tasksystem.service;

import com.springboot.tasksystem.dto.request.CustomerDto;
import com.springboot.tasksystem.dto.response.CustomerResponseDto;
import com.springboot.tasksystem.mapper.CustomerMapper;
import com.springboot.tasksystem.model.Customer;
import com.springboot.tasksystem.model.User;
import com.springboot.tasksystem.repository.CustomerRepository;
import com.springboot.tasksystem.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponseDto addCustomer(@Valid CustomerDto customerDto) {
        User user=new User();
        user.setUsername(customerDto.username());
        user.setPassword(passwordEncoder.encode(customerDto.password()));
        user=userRepository.save(user);

        Customer customer= CustomerMapper.mapDtotoEntity(customerDto);
        customer.setUser(user);

        return CustomerMapper.mapEntityToDto(customerRepository.save(customer));
    }
}
