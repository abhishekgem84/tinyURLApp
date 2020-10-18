package com.tiny.url.app.db.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversion {
	private static final String ALLOWEDSTRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] allowedCharacters = ALLOWEDSTRING.toCharArray();
	private int base = allowedCharacters.length;

	public String encode(long input) {
		StringBuilder encodedString = new StringBuilder();

		if (input == 0) {
			return String.valueOf(allowedCharacters[0]);
		}

		while (input > 0) {
			encodedString.append(allowedCharacters[(int) (input % base)]);
			input = input / base;
		}

		return encodedString.reverse().toString();
	}

	public long decode(String input) {
		char[] characters = input.toCharArray();
		int length = characters.length;

		int decoded = 0;

		// counter is used to avoid reversing input string
		int counter = 1;
		for (int i = 0; i < length; i++) {
			decoded += ALLOWEDSTRING.indexOf(characters[i]) * Math.pow(base, length - counter);
			counter++;
		}
		return decoded;
	}
}
