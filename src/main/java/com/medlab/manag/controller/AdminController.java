package com.medlab.manag.controller;

import com.medlab.manag.entity.Admin;
import com.medlab.manag.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Admin admin = adminService.login(body.get("username"), body.get("password"));
        if (admin == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
        return ResponseEntity.ok(admin);
    }
}