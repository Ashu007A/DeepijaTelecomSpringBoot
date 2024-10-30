package com.example.deepijaTel.Services.impl;

import com.example.deepijaTel.Models.TimeEntry;
import com.example.deepijaTel.Repositories.TimeEntryRepository;
import com.example.deepijaTel.Services.TimeEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeEntryServicesImpl implements TimeEntryServices {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    @Override
    public List<TimeEntry> getTimeEntriesByUsername(String username) {
        return timeEntryRepository.findByUsername(username);
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