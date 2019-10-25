package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.NoteRepository;

@Service
public class NoteService {
	
    @Autowired
    NoteRepository noteRepository;

	public List<Note> getNotes() {
		return noteRepository.getPublicNotes();
	}

}
