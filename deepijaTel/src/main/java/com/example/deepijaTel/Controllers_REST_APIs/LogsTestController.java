package com.example.deepijaTel.Controllers_REST_APIs;

import com.example.deepijaTel.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LogsTestController {

    private static final Logger logger = LoggerFactory.getLogger(LogsTestController.class);

    @Autowired
    private LogService logService;

    @GetMapping("/log-test")
    public String logTest() {
        logger.info("Test info log");
        logger.debug("Test debug log");
        logger.warn("Test warn log");
        logger.error("Test error log");
        logService.logCritical("Test critical log");
        return "Logging test executed!";
    }
}