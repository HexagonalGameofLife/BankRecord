package com.group4.bankSystem.services.CustomerServices;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String tc) throws UsernameNotFoundException {
        Customer customer = customerRepository.findOptionalByCustomerTC(tc)
            .orElseThrow(() -> new UsernameNotFoundException("Müşteri bulunamadı"));

        return new User(
            customer.getCustomerTC(),                    // username olarak TC
            customer.getLoginPasswordHash(),             // hashed şifre
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
