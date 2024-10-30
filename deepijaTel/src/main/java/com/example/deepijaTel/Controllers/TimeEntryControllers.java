package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.TimeEntry;
import com.example.deepijaTel.Services.TimeEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time_entries")
public class TimeEntryControllers {

    @Autowired
    private TimeEntryServices timeEntryServices;

    @GetMapping("/{username}")
    public List<TimeEntry> getTimeEntries(@PathVariable String username) {
        return timeEntryServices.getTimeEntriesByUsername(username);
    }

    @PostMapping("/punch_in")
    public TimeEntry punchIn(@RequestParam String username) {
        return timeEntryServices.punchIn(username);
    }

    @PostMapping("/punch_out")
    public TimeEntry punchOut(@RequestParam String username) {
        return timeEntryServices.punchOut(username);
    }

    @GetMapping("/status/{username}")
    public boolean getPunchStatus(@PathVariable String username) {
        return timeEntryServices.isPunchedIn(username);
    }
}