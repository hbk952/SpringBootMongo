package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.model.NoteDetails;
import com.example.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	private NoteRepository noteRepo;
	
	public List<NoteDetails> getNotes(){
//		System.out.println("inside get all notes");
		return noteRepo.findAll();
	}
	public List<NoteDetails> getByName(String name){
		return noteRepo.findBynoteName(name);
		
	}
	public Optional<NoteDetails> getById(int id){
		return noteRepo.findById(id);
		
	}
	
	
	public boolean deleteNoteById(int id) {
		Optional<NoteDetails> existing = noteRepo.findById(id);
		System.out.println(existing);
		if(existing.isEmpty()) {
			return false;
		}
		else {
			noteRepo.deleteById(id);
			return true;
		}
		
	}
	

	public NoteDetails updateNote(NoteDetails note) {
		boolean exist = noteRepo.existsById(note.getNoteId());
		if(exist) {
			noteRepo.deleteById(note.getNoteId());
			return noteRepo.save(note);
		}
		return null;
	}
	public NoteDetails saveNote(NoteDetails note) {
		return noteRepo.save(note);
	}
	public List<NoteDetails> saveAllNotes(List<NoteDetails> notes) {
		return noteRepo.saveAll(notes);
	}
	

}
