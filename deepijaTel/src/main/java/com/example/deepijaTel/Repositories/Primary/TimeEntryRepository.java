package com.example.deepijaTel.Repositories.Primary;

import com.example.deepijaTel.Models.Primary.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {
    List<TimeEntry> findByUsername(String username);
    TimeEntry findFirstByUsernameOrderByIdDesc(String username);
}