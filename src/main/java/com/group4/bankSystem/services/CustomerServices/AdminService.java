package com.group4.bankSystem.services.CustomerServices;

import com.group4.bankSystem.entities.CustomerEntities.Admin;

public interface AdminService {
    Admin login(String username, String password);
    boolean saveAdmin(Admin admin);
}
