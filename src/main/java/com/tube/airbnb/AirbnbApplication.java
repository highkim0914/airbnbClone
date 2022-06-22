package com.tube.airbnb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AirbnbApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AirbnbApplication.class)
				.properties("spring.config.location=" +
						"classpath:application.yml," +
						"optional:/config/application-real-db.yml")
				.run(args);
	}

}
