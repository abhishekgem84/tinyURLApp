package com.tiny.url.app.db.component;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
	@Override
	public String convert(LocalDateTime localDateTime) {
		return localDateTime.toString();
	}
}
