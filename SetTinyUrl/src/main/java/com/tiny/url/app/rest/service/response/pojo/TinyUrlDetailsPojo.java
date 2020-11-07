package com.tiny.url.app.rest.service.response.pojo;

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
public class TinyUrlDetailsPojo {
    @NotNull(message = "tinyURL should not be null.")
    @NotEmpty(message = "tinyURL should not be empty.")
    @Size(max = 200, min = 10, message = "tinyURL string should have min 10 and max 200 characters.")
    @Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp):\\/\\/)|(mailto:|news:))?(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:#@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:|:blank:]])?$", message = "Enter valid URL.")
    @Getter
    @Setter
    @Schema(description = "Tiny URL generated from Original URL", required = true, format = "String")
    private String tinyURL;

    @Size(max = 200, min = 10, message = "internalError string should have min 10 and max 200 characters.")
    @Getter
    @Setter
    @Schema(description = "Internal Error has happened and circuit is open", required = false, format = "String")
    private String internalError;
}
