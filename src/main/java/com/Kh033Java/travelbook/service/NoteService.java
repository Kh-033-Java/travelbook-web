package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;

public interface NoteService {

    void save(NoteDTO noteDTO, String countryName);

    Note findNoteForEditNote(NoteDTO noteDTO, int id);
}
