package nl.tudelft.oopp.server.services;

import org.slf4j.LoggerFactory;

public class LoggerService {

    public static <T> void info(Class<T> source, String message) {
        LoggerFactory.getLogger(source).info(message);
    }

    public static <T> void warn(Class<T> source, String message) {
        LoggerFactory.getLogger(source).warn(message);
    }

    public static <T> void error(Class<T> source, String message) {
        LoggerFactory.getLogger(source).error(message);
    }
}
