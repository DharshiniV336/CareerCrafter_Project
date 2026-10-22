package com.springboot.tasksystem.repository;

import com.springboot.tasksystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUserUsername(String customerUsername);
}
