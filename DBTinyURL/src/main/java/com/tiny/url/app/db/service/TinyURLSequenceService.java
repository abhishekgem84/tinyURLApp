package com.tiny.url.app.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tiny.url.app.db.exception.TinySequenceException;
import com.tiny.url.app.db.model.TinySequence;
import com.tiny.url.app.db.repository.TinyURLSequenceRepository;

@Service
public class TinyURLSequenceService {

	public static final String SEQUENCE_KEY = "tinyurlsequence";
	private MongoOperations mongoOperation;
	private TinyURLSequenceRepository tinyURLSequenceRepository;

	@Autowired
	public TinyURLSequenceService(MongoOperations mongoOperation, TinyURLSequenceRepository tinyURLSequenceRepository) {
		this.mongoOperation = mongoOperation;
		this.tinyURLSequenceRepository = tinyURLSequenceRepository;
	}

	public void setSequenceStore(String key) {
		if (!tinyURLSequenceRepository.findById(key).isPresent()) {
			tinyURLSequenceRepository.save(new TinySequence(key, 0));
		}
	}

	public long getNextSequenceId(String key) throws TinySequenceException {

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("sequence", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		TinySequence tinySequence = mongoOperation.findAndModify(query, update, options, TinySequence.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to generate.
		if (tinySequence == null) {
			throw new TinySequenceException("Unable to get sequence id for key : " + key);
		}

		return tinySequence.getSequence();

	}

	public void deleteAll() {
		tinyURLSequenceRepository.deleteAll();
	}
}
