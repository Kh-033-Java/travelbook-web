package com.Kh033Java.travelbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import com.Kh033Java.travelbook.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	private PhotoRepository photoRepository;

	@Autowired
	public PhotoServiceImpl(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}

	@Override
	@Cacheable("Photo")
	public List<Photo> findAllPublicPhotosForUnauthorized(String name) {
		return photoRepository.findAllPublicPhotosForUnauthorized(name);
	}

	@Override
	@Cacheable("Photo")
	public List<Photo> findAllPublicPhotosForAuthorized(String name, String login) {
		return photoRepository.findAllPublicPhotosForAuthorized(name, login);
	}

	@Override
	@Cacheable("Photo")
	public List<Photo> findAllUsersPhotosInCountry(String name, String userLogin) {
		return photoRepository.findAllUsersPhotosInCountry(name, userLogin);
	}

	public List<Photo> findAllPhotosByLinks(List<String> links) {
		List<Photo> allPhotos = new ArrayList<>();
		for (String link : links) {
			allPhotos.add(photoRepository.findPhotoByLink(link));
		}

		return allPhotos;
	}

	public Photo findUserAvatarByUserLogin(String login) {
		return photoRepository.findUserAvatarByUserId(login);
	}

	@Override
	@Cacheable("Photo")
	public List<Photo> findAllCountryPhotos(String countryName) {
		return photoRepository.getCountryPhotos(countryName);
	}

	@Override
	public boolean isPhotoExists(String photoLink) {
		if (photoRepository.findPhotoByLink(photoLink) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@CachePut("Photo")
	public Photo createPhoto(Photo photo) {
		return photoRepository.save(photo);
	}

}
