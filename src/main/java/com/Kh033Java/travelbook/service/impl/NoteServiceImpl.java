package com.Kh033Java.travelbook.service.impl;

import java.util.List;

import com.Kh033Java.travelbook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
	
    private final NoteRepository noteRepository;

	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@Override
	public List<Note> getNotes() {
		return noteRepository.getPublicNotes();
	}

}
