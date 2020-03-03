package nl.tudelft.oopp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
/* This annotation allows you to create a Spring Boot Application
    that can be easily run. It is a combination of 3 annotations:
    1) @SpringBootConfiguration -
    2) @ComponentScan - scanning for Beans and Components in the application
        By Default: It scans the package of the class
    3) @EnableAutoConfiguration - triggers all the auto configuration for Spring Boot

    @EnableJpaRepositories configures the scanning for Repository interfaces in the package of this class
* */
public class DemoApplication {

    /**
     * Runs the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Server running on port 8080");
    }

}

