package com.group4.bankSystem.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.CustomerEntities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
