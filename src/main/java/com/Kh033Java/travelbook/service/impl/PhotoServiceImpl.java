package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import com.Kh033Java.travelbook.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> findAllPublicPhotosForUnauthorized(String name) {
        return photoRepository.findAllPublicPhotosForUnauthorized(name);
    }

    @Override
    public List<Photo> findAllPublicPhotosForAuthorized(String name, String login) {
        return photoRepository.findAllPublicPhotosForAuthorized(name, login);
    }

    @Override
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
}
