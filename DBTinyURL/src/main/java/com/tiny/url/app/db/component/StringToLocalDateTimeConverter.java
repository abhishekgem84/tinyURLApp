package com.tiny.url.app.db.component;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String s) {
		return LocalDateTime.parse(s);
	}
}
