package com.Kh033Java.travelbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.service.NoteService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	private final NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@Override
	public void save(NoteDTO noteDTO, String countryName) {
		noteRepository.createNote(countryName, noteDTO.getTitle(), noteDTO.isPublic(), noteDTO.getDescription(), noteDTO.getDateOfVisiting(), noteDTO.getPeopleEstimate(),
				noteDTO.getPricesEstimate(), noteDTO.getCuisineEstimate(), noteDTO.getCommonImpression(), noteDTO.getDescribedCity(), noteDTO.getLogin(), noteDTO.getPhotoLink());
	}

	@Override
	public Note findNoteForEditNote(NoteDTO noteDTO, int id) {
		return noteRepository.findNoteForEdit(id, noteDTO.getTitle(), noteDTO.isPublic(), noteDTO.getDescription(), noteDTO.getDateOfVisiting(), noteDTO.getPeopleEstimate(),
				noteDTO.getPricesEstimate(), noteDTO.getCuisineEstimate(), noteDTO.getCommonImpression(), noteDTO.getDescribedCity(), noteDTO.getPhotoLink());
	}
}
