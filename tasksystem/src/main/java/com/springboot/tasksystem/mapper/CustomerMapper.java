package com.springboot.tasksystem.mapper;

import com.springboot.tasksystem.dto.request.CustomerDto;
import com.springboot.tasksystem.dto.response.CustomerResponseDto;
import com.springboot.tasksystem.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static Customer mapDtotoEntity(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setCity(customerDto.city());
        return customer;
    }
    public static CustomerResponseDto mapEntityToDto(Customer customer){
        return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getCity());
    }
}
