package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.dto.NoteDTO;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.repository.*;
import com.Kh033Java.travelbook.service.NoteService;
import com.Kh033Java.travelbook.service.PhotoService;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	private final NoteRepository noteRepository;
	private final UserRepository userRepository;
	private final CityRepository cityRepository;
	private final PhotoService photoService;

	public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, CityRepository cityRepository, PhotoService photoService) {
		this.noteRepository = noteRepository;
		this.userRepository = userRepository;
		this.cityRepository = cityRepository;
		this.photoService = photoService;
	}

	@Override
	public List<NoteDTO> getNotesConnectedToCountryForUnauthorizedUser(String name) {
		List<NoteDTO> notes = new ArrayList<>();
		for (Note note : noteRepository.findAllPublicNotesByCountry(name)) {
			NoteDTO noteDTO = createNoteDTO(note);
			notes.add(noteDTO);
		}

		return notes;
	}

	@Override
	public Set<NoteDTO> getCountryNotesAndUserPrivateNotes(String countryName, String login) {
		Set<NoteDTO> allNotes = new HashSet<>();
		List<Note> countryNotes = noteRepository.findAllPublicNotesByCountry(countryName);
		List<Note> userPrivateNotes = noteRepository.findAllUserPrivateNotesByCountry(countryName, login);
		for (Note note : countryNotes)
			allNotes.add(createNoteDTO(note));
		for (Note note : userPrivateNotes)
			allNotes.add(createNoteDTO(note));

		return allNotes;
	}

	@Override
	public NoteDTO getNoteById(Long id) {
		Note note = ValidationUtil.checkBeforeGet(noteRepository.findById(id), id, Note.class);
		return createNoteDTO(note);
	}

	@Override
	public List<NoteDTO> getPublicAndPrivateUserNotesConnectedToCountry(String countryName, String login) {
		List<NoteDTO> allNotes = new ArrayList<>();
		List<Note> publicNotes = noteRepository.findAllUserPublicNotesByCountry(countryName, login);
		List<Note> privateNotes = noteRepository.findAllUserPrivateNotesByCountry(countryName, login);
		for (Note note : publicNotes)
			allNotes.add(createNoteDTO(note));
		for (Note note : privateNotes)
			allNotes.add(createNoteDTO(note));

		return allNotes;
	}

	@Override
	public Note updateNote(NoteDTO noteDTO, long id) {
		Note note = ValidationUtil.checkBeforeGet(noteRepository.findById(id), id, Note.class);
		dtoToNote(noteDTO, note);
		noteRepository.save(note);

		return note;
	}

	@Override
	public void deleteNote(long id) {
		noteRepository.deleteById(id);
	}

	@Override
	public Note save(NoteDTO noteDTO) {
		Note note = new Note();
		dtoToNote(noteDTO, note);
		noteRepository.save(note);
		noteRepository.creatRelationshipBetweenUserAndNote(noteDTO.getLogin(), note.getId());

		return note;
	}

	private void dtoToNote(NoteDTO noteDTO, Note note) {
		note.setCommonImpression(noteDTO.getCommonImpression());
		note.setCuisineEstimate(noteDTO.getCuisineEstimate());
		note.setDateOfVisiting(noteDTO.getDateOfVisiting());
		note.setDescribedCity(cityRepository.findByName(noteDTO.getDescribedCity()));
		note.setDescription(noteDTO.getDescription());
		note.setIsPublic(noteDTO.isPublic());
		note.setPeopleEstimate(noteDTO.getPeopleEstimate());
		note.setPricesEstimate(noteDTO.getPricesEstimate());
		note.setTitle(noteDTO.getTitle());
		note.setPhotoLink(photoService.findAllPhotosByLinks(noteDTO.getPhotoLink()));
	}

	private NoteDTO createNoteDTO(Note note) {
		Note noteToDTO = ValidationUtil.checkBeforeGet(noteRepository.findById(note.getId()), note.getId(), Note.class);
		NoteDTO noteDTO = new NoteDTO();
		noteDTO.setId(noteToDTO.getId());
		noteDTO.setCommonImpression(noteToDTO.getCommonImpression());
		noteDTO.setCuisineEstimate(noteToDTO.getCuisineEstimate());
		noteDTO.setDateOfVisiting(noteToDTO.getDateOfVisiting());
		noteDTO.setDescribedCity(noteToDTO.getDescribedCity().getName());
		noteDTO.setLogin(userRepository.findUserByNoteId(noteToDTO.getId()).getLogin());
		noteDTO.setPeopleEstimate(noteToDTO.getPeopleEstimate());
		noteDTO.setPhotoLink(transformList(noteToDTO.getPhotoLink()));
		noteDTO.setPublic(noteToDTO.getIsPublic());
		noteDTO.setTitle(noteToDTO.getTitle());
		noteDTO.setPricesEstimate(noteToDTO.getPricesEstimate());
		noteDTO.setDescription(noteToDTO.getDescription());
		noteDTO.setLinkToUserAvatar(photoService.findUserAvatarByUserLogin(userRepository.findUserByNoteId(noteToDTO.getId()).getLogin()).getLink());

		return noteDTO;
	}

	private List<String> transformList(List<Photo> photoList) {
		List<String> links = new ArrayList<>();
		for (Photo photo : photoList) {
			links.add(photo.getLink());
		}

		return links;
	}
}
