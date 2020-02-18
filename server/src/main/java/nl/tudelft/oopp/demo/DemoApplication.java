package nl.tudelft.oopp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* This annotation allows you to create a Spring Boot Application
    that can be easily run. It is a combination of 3 annotations:
    1) @SpringBootConfiguration -
    2) @ComponentScan - scanning for Beans and Components in the application
        By Default: It scans the package of the class
    3) @EnableAutoConfiguration - triggers all the auto configuration for Spring Boot

* */
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Server running on port 8080");

    }

}
