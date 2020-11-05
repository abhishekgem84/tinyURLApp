package com.tiny.url.app.rest.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.service.TinyURLSequenceService;
import com.tiny.url.app.db.service.TinyURLService;
import com.tiny.url.app.rest.service.request.pojo.LongUrlDetailsPojo;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
@Slf4j
public class SaveAndGetTinyURL {

	private TinyURLService tinyURLService;
	private TinyURLSequenceService tinyURLSequenceService;

	@Autowired
	public SaveAndGetTinyURL(TinyURLService tinyURLService, TinyURLSequenceService tinyURLSequenceService) {
		this.tinyURLService = tinyURLService;
		this.tinyURLSequenceService = tinyURLSequenceService;
		this.tinyURLSequenceService.setSequenceStore(TinyURLSequenceService.SEQUENCE_KEY);
	}

	@Operation(summary = "Get tiny URL from original URL",tags = {"gettinyurl"})
	@PostMapping(path = "/getShortURL", consumes = "application/json", produces = "application/json")
	//@TimeLimiter(name = "application1") not implementing this as reactor package will bre required
	@CircuitBreaker(name = "application1", fallbackMethod = "fallback")
	public String convertToShortUrl(@Valid @RequestBody LongUrlDetailsPojo longUrlDetailsPojo)
			throws TinySequenceException {
		log.info("Request received ....");
		return tinyURLService.setTinyURL(longUrlDetailsPojo.getLongUrl());
	}

	private String fallback(Exception ex) {
		return "Getting Error in service " + ex.toString();
	}
}
