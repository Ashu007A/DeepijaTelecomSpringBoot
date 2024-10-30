package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.TimeEntry;
import java.util.List;

public interface TimeEntryServices {
    List<TimeEntry> getTimeEntriesByUsername(String username);
    TimeEntry punchIn(String username);
    TimeEntry punchOut(String username);
    boolean isPunchedIn(String username);
}