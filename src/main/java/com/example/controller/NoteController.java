package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.NoteDetails;
import com.example.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteService noteServ;
	
	@GetMapping("/getAll")
	public List<NoteDetails> getAllNotes(){
		System.out.println("inside getAllNotes()");
		return noteServ.getNotes();
	}
	@GetMapping("/getById")
	public ResponseEntity<?> getById(@RequestParam("noteId") Integer id){
		Optional<NoteDetails> note =  noteServ.getById(id);
		
		if(note.isPresent()) {
			return new ResponseEntity<Optional<NoteDetails>>(note,HttpStatus.FOUND);
			
		}
		else{
			return new ResponseEntity<String>("Note with the given ID not found",HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/getByName")
	public ResponseEntity<?> getByName(@RequestParam("noteName") String name){
		List<NoteDetails> note =  noteServ.getByName(name);
		if(note.size() == 0) {
			return new ResponseEntity<String>("Note with the given name not found",HttpStatus.BAD_REQUEST);
			
			
		}
		else {
			return new ResponseEntity<List<NoteDetails>>(note,HttpStatus.FOUND);
		}
		
	}
	@PostMapping(value="/save", consumes="application/json ; charset=utf-8")
	public ResponseEntity<?> saveNote(@RequestBody NoteDetails note){
		Optional<NoteDetails> isNoteExisting = noteServ.getById(note.getNoteId());
		if(!isNoteExisting.isPresent()){
			NoteDetails savedNote = noteServ.saveNote(note);
			if(!savedNote.equals(null)) {
				return new ResponseEntity<NoteDetails>(savedNote,HttpStatus.CREATED);
			
			}
			else {
				return new ResponseEntity<String>("Note not saved",HttpStatus.BAD_REQUEST);
			}
			
		}
		else {
			return new ResponseEntity<String>("Employee already exisit",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value="/saveAll", consumes="application/json ; charset=utf-8")
	public ResponseEntity<?> saveAllNote(@RequestBody List<NoteDetails> notes){
		
		if(notes.size() == 0) {
		
			return new ResponseEntity<String>("Empty List provided",HttpStatus.BAD_REQUEST);
		}
		else {
			List<NoteDetails> noteList = noteServ.saveAllNotes(notes);
			if(noteList.size() != 0) {
				return new ResponseEntity<List<NoteDetails>>(noteList,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("Unable to save all Notes",HttpStatus.BAD_REQUEST);
			}
			
		}
	}
	@PutMapping("/updateNote")	
	public ResponseEntity<?> updateNote(@RequestBody NoteDetails note){
		NoteDetails updatedNote = noteServ.updateNote(note);
		if(!updatedNote.equals(null)) {
			return new ResponseEntity<NoteDetails>(updatedNote,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Note not updated",HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteNote")
	public ResponseEntity<?> deleteNote(@RequestParam("noteId") Integer id){
		boolean result = noteServ.deleteNoteById(id);
		if(result) {
			return new ResponseEntity<String>("Note deleted succesfully",HttpStatus.GONE);
		}
		else {
			return new ResponseEntity<String>("Note doesn't exist",HttpStatus.BAD_REQUEST);
		}
		
	}
}
