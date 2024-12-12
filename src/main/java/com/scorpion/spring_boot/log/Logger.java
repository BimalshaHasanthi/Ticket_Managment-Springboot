package com.scorpion.spring_boot.log;

public interface Logger {
    void log(String level, String message);
    void info(String message);
    void warn(String message);
    void error(String message);
}
