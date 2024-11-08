package com.example.deepijaTel.Repositories.Secondary;

import com.example.deepijaTel.Models.Secondary.ConVoxLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConVoxRepository extends JpaRepository<ConVoxLogin, Integer> {
    ConVoxLogin findByUsername(String username);
}