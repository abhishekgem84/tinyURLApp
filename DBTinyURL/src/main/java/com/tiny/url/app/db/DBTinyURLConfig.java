package com.tiny.url.app.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.tiny.url.app.db.component.LocalDateTimeToStringConverter;
import com.tiny.url.app.db.component.StringToLocalDateTimeConverter;

@SpringBootApplication
public class DBTinyURLConfig {

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new LocalDateTimeToStringConverter());
		converters.add(new StringToLocalDateTimeConverter());
		return new MongoCustomConversions(converters);
	}

	public static void main(String... args) {
		SpringApplication app = new SpringApplication(DBTinyURLConfig.class);
		app.run(args);
	}
}
