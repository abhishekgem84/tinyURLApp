package com.tiny.url.app.db.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tiny.url.app.db.service.BaseConversion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseConversionTest {
	@Autowired
	private BaseConversion baseConversion;

	@Test
	public void decodeString() {
		assertEquals(10, baseConversion.decode("k"));

	}

	@Test
	public void encodeString() {
		assertEquals("k", baseConversion.encode(10));

	}
	
	@Test
	public void encodeZeroString() {
		assertEquals("a", baseConversion.encode(0));

	}
}
