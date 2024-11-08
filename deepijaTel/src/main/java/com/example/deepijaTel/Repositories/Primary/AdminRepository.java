package com.example.deepijaTel.Repositories.Primary;

import com.example.deepijaTel.Models.Primary.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}