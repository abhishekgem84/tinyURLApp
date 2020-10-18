package com.tiny.url.app.db.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "tinyurl")
@ToString
public class TinyURL {
	@Id
	@Field(targetType = FieldType.INT64, order = 1, name = "id")
	@Getter
	@Setter
	private long id;
	@Field(targetType = FieldType.STRING, order = 1, name = "url")
	@Getter
	@Setter
	private String url;
	@Field(targetType = FieldType.TIMESTAMP, order = 2, name = "startdate")
	@Getter
	@Setter
	private LocalDateTime startDate = LocalDateTime.now();
	@Field(targetType = FieldType.TIMESTAMP, order = 3, name = "enddate")
	@Getter
	@Setter
	private LocalDateTime endDate = LocalDateTime.now().plusYears(5);

	public TinyURL(long id, String url) {
		this.id = id;
		this.url = url;
	}
}
