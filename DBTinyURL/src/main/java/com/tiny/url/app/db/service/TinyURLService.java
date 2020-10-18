package com.tiny.url.app.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.model.TinyURL;
import com.tiny.url.app.db.repository.TinyURLRepository;

@Service
public class TinyURLService {
	private static final Logger logger = LoggerFactory.getLogger(TinyURLService.class);
	private final TinyURLRepository tinyURLRpository;
	private final BaseConversion baseConversion;
	private final TinyURLSequenceService tinyURLSequenceService;

	@Autowired
	public TinyURLService(TinyURLRepository tinyURLRpository, BaseConversion baseConversion,
			TinyURLSequenceService tinyURLSequenceService) {
		this.tinyURLRpository = tinyURLRpository;
		this.baseConversion = baseConversion;
		this.tinyURLSequenceService = tinyURLSequenceService;
	}

	public String setTinyURL(final String url) throws TinySequenceException {
		TinyURL tinyURL = new TinyURL(tinyURLSequenceService.getNextSequenceId(TinyURLSequenceService.SEQUENCE_KEY),
				url);
		if (logger.isInfoEnabled())
			logger.info(tinyURL.toString());
		return baseConversion.encode(tinyURLRpository.save(tinyURL).getId());
	}

	public String getActualURL(final String tinyURLKey) {
		return tinyURLRpository.findByid(baseConversion.decode(tinyURLKey)).getUrl();
	}

	public void deleteAll() {
		tinyURLRpository.deleteAll();
	}
}
