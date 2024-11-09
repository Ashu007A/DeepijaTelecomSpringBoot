package com.example.deepijaTel.Controllers_REST_APIs;

import com.example.deepijaTel.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogsController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public String logExample() {
        logService.logInfo("Info message from /log-example");
        logService.logDebug("Debug message from /log-example");
        logService.logWarning("Warning message from /log-example");
        logService.logError("Error message from /log-example");
        logService.logCritical("Critical message from /log-example");
        return "Logging example executed!";
    }
}
