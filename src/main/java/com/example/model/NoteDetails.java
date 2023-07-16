package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NoteDetails {
	@Id
	private int noteId;
	private String noteName;
	private String noteDesc;
	public NoteDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoteDetails(int noteId, String noteName, String noteDesc) {
		super();
		this.noteId = noteId;
		this.noteName = noteName;
		this.noteDesc = noteDesc;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteDesc() {
		return noteDesc;
	}
	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
	}
	@Override
	public String toString() {
		return "NoteDetails [noteId=" + noteId + ", noteName=" + noteName + ", noteDesc=" + noteDesc + "]";
	}
	

}
