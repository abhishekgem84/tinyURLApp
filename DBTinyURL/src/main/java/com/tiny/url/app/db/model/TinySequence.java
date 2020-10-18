package com.tiny.url.app.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "tinysequence")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TinySequence {
	@Id
	@Getter
	@Setter
	private String id;
	@Getter
	@Setter
	private long sequence;
	@Version
	@Getter
	@Setter
	private long version;

	public TinySequence(String id, long sequence) {
		this.id = id;
		this.sequence = sequence;
	}
}
