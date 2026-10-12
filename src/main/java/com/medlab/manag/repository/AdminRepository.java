package com.medlab.manag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medlab.manag.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsernameAndPassword(String username, String password);
}