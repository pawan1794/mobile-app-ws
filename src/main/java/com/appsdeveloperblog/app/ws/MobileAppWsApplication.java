package com.appsdeveloperblog.app.ws;

import com.appsdeveloperblog.app.ws.security.AppProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MobileAppWsApplication extends SpringBootServletInitializer {

	private static final Logger logger = LogManager.getLogger(MobileAppWsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
		logger.info("mobile-app-ws started on port : " + 5555);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MobileAppWsApplication.class);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean
	AppProperties getAppProperties() {
		return new AppProperties();
	}

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
