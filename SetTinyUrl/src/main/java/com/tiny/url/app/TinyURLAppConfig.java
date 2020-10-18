package com.tiny.url.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinyURLAppConfig {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TinyURLAppConfig.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

}
