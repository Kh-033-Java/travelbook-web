package com.Kh033Java.travelbook.service;

import java.util.List;

import com.Kh033Java.travelbook.entity.Photo;

public interface PhotoService {
    List<Photo> findAllPublicPhotosForUnauthorized(String name);

    List<Photo> findAllPublicPhotosForAuthorized(String name, String login);

    List<Photo> findAllUsersPhotosInCountry(String name, String userLogin);

    List<Photo> findAllPhotosByLinks(List<String> links);

    Photo findUserAvatarByUserLogin(String userLogin);

	List<Photo> findAllCountryPhotos(String countryName);
}
