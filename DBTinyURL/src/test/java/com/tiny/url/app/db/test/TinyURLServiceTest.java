package com.tiny.url.app.db.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.service.TinyURLSequenceService;
import com.tiny.url.app.db.service.TinyURLService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyURLServiceTest {
	@Autowired
	private TinyURLService _tinyURLService;
	@Autowired
	private TinyURLSequenceService _tinyURLSequenceService;
	// Variable are re initialized very time if new test case runs to static makes them common or sharable between them.
	private static boolean flag = false;

	@Before
	public void setup() {
		if (!flag) {
			cleanup();
			_tinyURLSequenceService.setSequenceStore("tinyurlsequence");
			flag = true;
		}
	}

	public void cleanup() {
		_tinyURLService.deleteAll();
		_tinyURLSequenceService.deleteAll();
	}

	@Test
	public void aSetTinyUrl() throws TinySequenceException {
		assertEquals("b", _tinyURLService.setTinyURL("http://www.yahoo.com2"));
	}

	@Test
	public void bGetActualUrl() throws TinySequenceException {
		assertEquals("http://www.yahoo.com2", _tinyURLService.getActualURL("b"));
	}
}
