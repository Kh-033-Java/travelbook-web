package com.Kh033Java.travelbook.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.service.NoteService;

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
    public List<NoteDTO> getAllPublicNotes(@PathVariable String name) {
        return noteService.getNotesConnectedToCountryForUnauthorizedUser(name);
    }

    @GetMapping("/country/{name}/notes/{login}")
    public @ResponseBody
    Set<NoteDTO> getAllPrivateAndPublicNotes(@PathVariable String name, @PathVariable String login) {
        return noteService.getCountryNotesAndUserPrivateNotes(name, login);
    }

    @GetMapping("country/notes/{id}")
    public @ResponseBody
    NoteDTO getNoteById(@PathVariable long id) {
        return noteService.getNoteById(id);
    }


    @GetMapping(value = "country/{name}/notes/private")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDTO> getAllUsersPlans(@PathVariable String name, @RequestParam String user) {
        return noteService.getPublicAndPrivateUserNotesConnectedToCountry(name, user);
    }


    @PostMapping(value = "notes")
    public Note saveNote(@RequestBody NoteDTO noteDTO) {
        return noteService.save(noteDTO);
    }

    @DeleteMapping(value = "notes/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    @PutMapping(value = "notes/{id}")
    public Note editPlan(@RequestBody NoteDTO noteDTO, @PathVariable Long id) {
        return noteService.updateNote(noteDTO, id);
    }

    @PutMapping("/notes/{id}/like/{login}")
    public @ResponseBody
    void likeNoteByUser(@PathVariable int id, @PathVariable String login) {
        Note note = noteRepository.checkIfExistLike(login, id);
        if(note == null){
            noteRepository.findNoteForLike(login, id);
        }else{
            noteRepository.disLikeNote(login, id);
        }
    }

    @GetMapping("/notes/{id}/likes")
    public int getAllLikesbyNote(@PathVariable int id){
        return noteRepository.findNumberOfLikes(id);
    }

    @GetMapping("/notes/{id}/liked/{login}")
    public boolean checkIfLiked(@PathVariable int id, @PathVariable String login){
        Note note = noteRepository.checkIfExistLike(login, id);
        if(note == null){
            return false;
        }else{
            return true;
        }
    }
}
