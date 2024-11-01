package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.Admin;
import com.example.deepijaTel.Models.User;

import java.util.List;

public interface AdminServices {
    String authenticateAdmin(String username, String password);
    Admin getAdminByUsername(String username);

    List<User> getAllUsers();

    void addUser(User user);

    // Edit User
    User getUserById(Long id);
    void updateUser(User user);

    // Delete User
    String deleteUserById(Long id);
}
