package com.tiny.url.app.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tiny.url.app.db.model.TinySequence;

@Repository
public interface TinyURLSequenceRepository extends MongoRepository<TinySequence, String> {
	TinySequence findByid(String key);
}
