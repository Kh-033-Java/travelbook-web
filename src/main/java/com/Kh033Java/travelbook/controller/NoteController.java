package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/country/{name}/notes")
    public @ResponseBody
    List<Note> getAllPublicNotes(@PathVariable String name) {
        return noteRepository.findAllPublicNotesForUnauthorizedUser(name);
    }

    @GetMapping("/country/{name}/notes/{login}")
    public @ResponseBody
    List<Note> getAllPrivateAndPublicNotes(@PathVariable String name, @PathVariable String login) {
        return noteRepository.findAllNotesForAuthorizedUser(name, login);
    }

    @GetMapping("/notes/{id}")
    public @ResponseBody
    List<Note> getPublicNoteById(@PathVariable int id) {
        return noteRepository.findPublicNoteById(id);
    }

    @GetMapping("/notes/{id}/user/{login}")
    public @ResponseBody
    List<Note> getPrivateNoteById(@PathVariable int id, @PathVariable String login) {
        return noteRepository.findPrivateNoteById(id, login);
    }

    @PostMapping("/country/{name}/notes")
    public @ResponseBody
    List<Note> createNote(@PathVariable String name, @RequestBody NoteDTO noteDTO) {
        return noteRepository.createNote(name, noteDTO.getTitle(), noteDTO.isPublic(), noteDTO.getDescription(), noteDTO.getDateOfVisiting(), noteDTO.getPeopleEstimate(),
                noteDTO.getPricesEstimate(), noteDTO.getCuisineEstimate(), noteDTO.getCommonImpression(), noteDTO.getName(), noteDTO.getLogin(), noteDTO.getLink());
    }

    @DeleteMapping("/notes/{id}/user/{login}")
    public @ResponseBody
    List<Note> getDeleteNoteById(@PathVariable int id, @PathVariable String login) {
        return noteRepository.findNoteForDelete(id, login);
    }

    @PatchMapping("/notes/{id}")
    public @ResponseBody
    List<Note> findNoteForEdit(@PathVariable int id, @RequestBody NoteDTO noteDTO) {
        return noteRepository.findNoteForEdit(id, noteDTO.getTitle(), noteDTO.isPublic(), noteDTO.getDescription(), noteDTO.getDateOfVisiting(), noteDTO.getPeopleEstimate(),
                noteDTO.getPricesEstimate(), noteDTO.getCuisineEstimate(), noteDTO.getCommonImpression());
    }

    @PutMapping("/notes/{id}/like")
    public @ResponseBody
    List<Note> getNoteForLike(@PathVariable int id, @RequestBody NoteDTO noteDTO) {
        return noteRepository.findNoteForLike(noteDTO.getLogin(), id);
    }
}
