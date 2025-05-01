package com.group4.bankSystem.repository.CustomerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.CustomerEntities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Customer findByCustomerTC(String customerTC);
  Customer findByCustomerPhoneNumber(String customerPhoneNumber);
  Customer findByCustomerEmail(String customerEmail);
}
