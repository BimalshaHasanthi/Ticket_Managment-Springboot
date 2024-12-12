package com.scorpion.spring_boot.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFormat {

    private final LocalDateTime timestamp;
    private final String status;
    private final String message;

    public LogFormat(String status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    // Format the logs for dates and texts
    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] [%s] %s", timestamp.format(formatter), status, message);
    }
}
