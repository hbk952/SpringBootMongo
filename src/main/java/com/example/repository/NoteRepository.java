package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.NoteDetails;

@Repository
public interface NoteRepository extends MongoRepository<NoteDetails, Integer> {
	List<NoteDetails> findBynoteName(String noteName);

}
