package com.example.deepijaTel.Services.Primary.Implementations;

import com.example.deepijaTel.Models.Primary.Admin;
import com.example.deepijaTel.Models.Primary.User;
import com.example.deepijaTel.Repositories.Primary.AdminRepository;
import com.example.deepijaTel.Repositories.Primary.UserRepository;
import com.example.deepijaTel.Services.Primary.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

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

    @Override
    public void addUser(User user) {
        // Hash the password before saving
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }
}
