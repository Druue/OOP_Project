package nl.tudelft.oopp.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {

    public static void info(Class source, String message) {
        LoggerFactory.getLogger(source).info(message);
    }

    public static void warn(Class source, String message) {
        LoggerFactory.getLogger(source).warn(message);
    }

    public static void error(Class source, String message) {
        LoggerFactory.getLogger(source).error(message);
    }
}
