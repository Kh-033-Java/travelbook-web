package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;

import java.util.List;
import java.util.Set;

public interface NoteService {

    List<NoteDTO> getNotesConnectedToCountryForUnauthorizedUser(String name);

    Set<NoteDTO> getCountryNotesAndUserPrivateNotes(String countryName, String login);

    NoteDTO getNoteById(Long id);

    List<NoteDTO> getPublicAndPrivateUserNotesConnectedToCountry(String countryName, String login);

    Note updateNote(NoteDTO plan, long id);

    void deleteNote(long id);

    Note save(NoteDTO noteDTO);
}
