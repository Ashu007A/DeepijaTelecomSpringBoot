package com.example.deepijaTel.Services.Implementations;

import com.example.deepijaTel.Models.Primary.TimeEntry;
import com.example.deepijaTel.Repositories.Primary.TimeEntryRepository;
import com.example.deepijaTel.Services.TimeEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeEntryServicesImpl implements TimeEntryServices {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    @Override
    public List<TimeEntry> getTimeEntriesByUsername(String username) {
//        return timeEntryRepository.findByUsername(username);
        List<TimeEntry> allEntries = timeEntryRepository.findByUsername(username);
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return allEntries.stream()
                .filter(entry -> {
                    LocalDate entryDate = LocalDate.parse(entry.getPunchIn().substring(0, 10), formatter);
                    return entryDate.equals(today);
                })
                .collect(Collectors.toList());
    }

    @Override
    public TimeEntry punchIn(String username) {
        if (isPunchedIn(username)) {
            return null; // Handle already punched-in case
        }
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setUsername(username);
        timeEntry.setPunchIn(LocalDateTime.now().toString());
        return timeEntryRepository.save(timeEntry);
    }

    @Override
    public TimeEntry punchOut(String username) {
        TimeEntry timeEntry = timeEntryRepository.findFirstByUsernameOrderByIdDesc(username);
        if (timeEntry == null || timeEntry.getPunchOut() != null) {
            return null; // Handle no punch-in found case
        }
        timeEntry.setPunchOut(LocalDateTime.now().toString());
        return timeEntryRepository.save(timeEntry);
    }

    @Override
    public boolean isPunchedIn(String username) {
        TimeEntry timeEntry = timeEntryRepository.findFirstByUsernameOrderByIdDesc(username);
        return timeEntry != null && timeEntry.getPunchOut() == null;
    }
}