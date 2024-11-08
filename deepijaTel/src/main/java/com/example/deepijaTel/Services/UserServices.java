package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.Primary.User;

public interface UserServices {

    // RegistrationService methods
    String registerUser(User user);

    // LoginService methods
    String loginUser(String username, String password);

    // UserService methods
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

    // Update profile
    void updateUserByUsername(String username, User updatedUser);

    // Delete account
    void deleteUserByUsername(String username);

    // Change username
    String changeUsername(String oldUsername, String newUsername);

    //change password
    String changePassword(String username, String oldPassword, String newPassword, String confirmPassword);
}