package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAllPublicPhotosForUnauthorized(String name);

    List<Photo> findAllPublicPhotosForAuthorized(String name, String login);

    List<Photo> findAllUsersPhotosInCountry(String name, String userLogin);
}
