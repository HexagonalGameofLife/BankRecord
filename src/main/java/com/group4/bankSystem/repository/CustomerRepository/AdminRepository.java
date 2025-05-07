package com.group4.bankSystem.repository.CustomerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.CustomerEntities.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
