package com.medlab.manag.service;

import org.springframework.stereotype.Service;

import com.medlab.manag.entity.Admin;
import com.medlab.manag.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}