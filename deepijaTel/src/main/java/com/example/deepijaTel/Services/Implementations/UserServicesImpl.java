package com.example.deepijaTel.Services.Implementations;

import com.example.deepijaTel.Models.Primary.User;
import com.example.deepijaTel.Repositories.Primary.UserRepository;
import com.example.deepijaTel.Services.UserServices;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServicesImpl implements UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public String loginUser(String username, String password) {
        logger.info("Fetching user by username: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.warn("User not found: {}", username);
            return "Invalid username or password!";
        }
        logger.info("User found: {}. Verifying password.", username);
        if (BCrypt.checkpw(password, user.getPassword())) {
            logger.info("Password verified for user: {}", username);
            return "Login successful!";
        } else {
            logger.warn("Password verification failed for user: {}", username);
            return "Invalid username or password!";
        }
    }

    @Override
    public String registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            return "Phone number already exists!";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "New account created successfully!";
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserByUsername(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setState(updatedUser.getState());
            existingUser.setDistrict(updatedUser.getDistrict());
            existingUser.setCity(updatedUser.getCity());
            existingUser.setDob(updatedUser.getDob());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setCourse(updatedUser.getCourse());
            existingUser.setAddress(updatedUser.getAddress());
            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public String changeUsername(String oldUsername, String newUsername) {
        if (userRepository.existsByUsername(newUsername)) {
            return "Username already exists!";
        }
        User user = userRepository.findByUsername(oldUsername);
        if (user != null) {
            user.setUsername(newUsername);
            userRepository.save(user);
            return "Username changed successfully!";
        }
        return "Error updating username!";
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return "New passwords do not match!";
        }
        User user = userRepository.findByUsername(username);
        if (user != null && BCrypt.checkpw(oldPassword, user.getPassword())) {
            user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            userRepository.save(user);
            return "Password changed successfully!";
        }
        return "Old password is incorrect!";
    }
}