package com.tiny.url.app.db.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.service.TinyURLSequenceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyURLSequenceServiceTest {
	@Autowired
	private TinyURLSequenceService tinyURLSequenceService;

	@Test(expected = TinySequenceException.class)
	public void getNextSequenceIdTest() throws TinySequenceException {
		tinyURLSequenceService.getNextSequenceId("test");
	}
}
