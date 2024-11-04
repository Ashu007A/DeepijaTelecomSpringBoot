package com.example.deepijaTel.Repositories;

import com.example.deepijaTel.Models.ConVoxLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConVoxRepository extends JpaRepository<ConVoxLogin, Integer> {
    ConVoxLogin findByUsername(String username);
}