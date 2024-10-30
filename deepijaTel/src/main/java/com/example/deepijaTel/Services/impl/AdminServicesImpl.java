package com.example.deepijaTel.Services.impl;

import com.example.deepijaTel.Models.Admin;
import com.example.deepijaTel.Models.User;
import com.example.deepijaTel.Repositories.AdminRepository;
import com.example.deepijaTel.Repositories.UserRepository;
import com.example.deepijaTel.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            return "Username or password is incorrect!";
        }
        if (!admin.getPassword().equals(password)) {
            return "Invalid password.";
        }
        return "Login successful!";
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
