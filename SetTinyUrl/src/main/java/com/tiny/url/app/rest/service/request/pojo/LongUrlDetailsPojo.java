package com.tiny.url.app.rest.service.request.pojo;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LongUrlDetailsPojo {

	@NotNull(message = "URL should not be null.")
	@NotEmpty(message = "URL should not be empty string.")
	@Size(max = 200, min = 10, message = "URL string should have min 10 and max 200 characters.")
	@Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp):\\/\\/)|(mailto:|news:))?(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:#@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:|:blank:]])?$", message = "Enter valid URL.")
	@Getter
	@Setter
	@Schema(description = "Original URL that needs to be made tiny", required = true, format = "String")
	private String longUrl;
	@Getter
	@Setter
	@Schema(description = "Validity of Generated Tiny URL", required = false, format = "LocalDate")
	private LocalDate expiryDate = LocalDate.now().plusYears(5);

}
