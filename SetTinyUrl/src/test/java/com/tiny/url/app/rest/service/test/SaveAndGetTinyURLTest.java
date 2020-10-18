package com.tiny.url.app.rest.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.service.TinyURLSequenceService;
import com.tiny.url.app.db.service.TinyURLService;
import com.tiny.url.app.rest.service.SaveAndGetTinyURL;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.StringContains;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SaveAndGetTinyURL.class)
public class SaveAndGetTinyURLTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private TinyURLService tinyURLService;
	@MockBean
	private TinyURLSequenceService tinyURLSequenceService;

	@Test
	public void convertToShortUrlTest200() throws TinySequenceException, Exception {
		given(tinyURLService.setTinyURL(Mockito.anyString())).willReturn("a");

		mvc.perform(post("/api/v1/getShortURL").contentType(MediaType.APPLICATION_JSON)
				.content("{\"longUrl\":\"https://www.youtube.com\",\"expiryDate\":\"2020-01-01\"}"))
				.andExpect(status().is2xxSuccessful()).andExpect(content().string("a"));
		verify(tinyURLService, VerificationModeFactory.times(1)).setTinyURL(Mockito.any());
		reset(tinyURLService);
	}

	@Test
	public void convertToShortUrlTest400() throws TinySequenceException, Exception {
		given(tinyURLService.setTinyURL(Mockito.anyString())).willReturn("a");

		mvc.perform(post("/api/v1/getShortURL").contentType(MediaType.APPLICATION_JSON).content("{\"longUrl\":\"\"}"))
				.andExpect(status().is4xxClientError()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.longUrl",
						anyOf(StringContains.containsString("URL string should have min 10 and max 200 characters"),
								StringContains.containsString("URL should not be empty string"),
								StringContains.containsString("Enter valid URL"))));
		verify(tinyURLService, VerificationModeFactory.times(0)).setTinyURL(Mockito.any());
		reset(tinyURLService);
	}
	
	@Test
	public void convertToShortUrlTestInvalidURL() throws TinySequenceException, Exception {
		given(tinyURLService.setTinyURL(Mockito.anyString())).willReturn("a");

		mvc.perform(post("/api/v1/getShortURL").contentType(MediaType.APPLICATION_JSON).content("{\"longUrl\":\"255.255.255.255\"}"))
				.andExpect(status().is4xxClientError()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.longUrl",
						anyOf(StringContains.containsString("Enter valid URL"))));
		verify(tinyURLService, VerificationModeFactory.times(0)).setTinyURL(Mockito.any());
		reset(tinyURLService);
	}

}
