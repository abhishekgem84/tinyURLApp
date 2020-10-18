package com.tiny.url.app.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tiny.url.app.db.model.TinyURL;

@Repository
public interface TinyURLRepository extends MongoRepository<TinyURL, String> {
	TinyURL findByid(long id);
}
