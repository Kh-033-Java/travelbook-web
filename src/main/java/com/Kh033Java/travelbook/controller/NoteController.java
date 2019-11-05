package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private NoteRepository noteRepository;
    private NoteService noteService;

    @Autowired
    public NoteController(NoteRepository noteRepository, NoteService noteService) {
        this.noteRepository = noteRepository;
        this.noteService = noteService;
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
    Note getPublicNoteById(@PathVariable int id) {
        return noteRepository.findPublicNoteById(id);
    }

    @GetMapping("/notes/{id}/user/{login}")
    public @ResponseBody
    List<Note> getPrivateNoteById(@PathVariable int id, @PathVariable String login) {
        return noteRepository.findNotesByIdByUser(id, login);
    }

    @PostMapping("/country/{countryName}/notes")
    public @ResponseBody
    void createNote(@PathVariable String countryName, @RequestBody NoteDTO noteDTO) {
       noteService.save(noteDTO, countryName);
    }

    @DeleteMapping("/notes/{id}/user/{login}")
    public @ResponseBody
    void getDeleteNoteById(@PathVariable int id, @PathVariable String login) {
        noteRepository.findNoteForDelete(id, login);
    }

    @PatchMapping("/notes/{id}")
    public @ResponseBody
    Note getNoteForEdit(@PathVariable int id, @RequestBody NoteDTO noteDTO) {
       return noteService.findNoteForEditNote(noteDTO, id);
    }

    @PutMapping("/notes/{id}/like")
    public @ResponseBody
    void likeNoteByUser(@PathVariable int id, @RequestBody NoteDTO noteDTO) {
        noteRepository.findNoteForLike(noteDTO.getLogin(), id);
    }
}
