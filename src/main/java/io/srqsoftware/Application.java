package io.srqsoftware;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * The main entry point into a Spring Boot Application
 *
 * @author tdc
 *
 */
@SpringBootApplication
// The following might be required if the data source shouldn't be auto-configured
/* @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) */
public class Application extends SpringBootServletInitializer {
	static final Logger logger = Logger.getLogger(Application.class);

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
}
