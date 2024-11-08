package com.example.deepijaTel.Repositories.Primary;

import com.example.deepijaTel.Models.Primary.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    List<User> findAll();
}