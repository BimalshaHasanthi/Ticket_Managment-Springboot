package com.scorpion.spring_boot.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogFile implements Logger {
    private final String LOG_DIR_PATH = "src/main/java/com/scorpion/spring_boot/log";
    private final String LOG_FILE_NAME = "log_file.txt";

    public LogFile() {
        // Define the directory and file path
        Path logDir = Paths.get(LOG_DIR_PATH);
        Path filePath = logDir.resolve(LOG_FILE_NAME);

        try {
            // Create the directory if it doesn't exist
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Write Logs in the text file
    @Override
    public void log(String level, String message) {
        LogFormat entry = new LogFormat(level, message);
        String content = entry.format() + "\n";

        // Define the directory and file path
        Path logDir = Paths.get(LOG_DIR_PATH);
        Path filePath = logDir.resolve(LOG_FILE_NAME);

        try {
            // Write content to the file, creating or overwriting the file
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Log saved to " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void info(String message) {
        log("INFO : ", message);
    }

    @Override
    public void warn(String message) {
        log("WARN : ", message);
    }

    @Override
    public void error(String message) {
        log("ERROR : ", message);
    }
}
