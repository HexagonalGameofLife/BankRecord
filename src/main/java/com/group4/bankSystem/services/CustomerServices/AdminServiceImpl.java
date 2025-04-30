package com.group4.bankSystem.services.CustomerServices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.CustomerEntities.Admin;
import com.group4.bankSystem.repository.CustomerRepository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin login(String username, String password) {
        return adminRepository.findByUsername(username)
            .filter(admin -> admin.getPasswordHash().equals(password)) // gerçek uygulamada hash kontrolü yapılmalı
            .orElse(null);
    }

    @Override
    public boolean saveAdmin(Admin admin) {
      Optional<Admin> existing = adminRepository.findByUsername(admin.getUsername());
      if (existing.isPresent()) {
          return false; // Kullanıcı adı zaten var
      }
      // Gerçek sistemde şifre hash'lenmelidir
      adminRepository.save(admin);
      return true;
  }
}
