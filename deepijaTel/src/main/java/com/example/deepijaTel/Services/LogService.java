package com.example.deepijaTel.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    private static final Logger criticalLogger = LoggerFactory.getLogger("criticalLogger");
    private static final Marker CRITICAL_MARKER = MarkerFactory.getMarker("CRITICAL");

    @Async
    public void logInfo(String message) {
        logger.info(message);
    }

    @Async
    public void logDebug(String message) {
        logger.debug(message);
    }

    @Async
    public void logWarning(String message) {
        logger.warn(message);
    }

    @Async
    public void logError(String message) {
        logger.error(message);
    }

    @Async
    public void logCritical(String message) {
        criticalLogger.error(CRITICAL_MARKER, "[CRITICAL] " + message);
    }
}