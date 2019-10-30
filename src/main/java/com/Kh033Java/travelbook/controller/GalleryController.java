package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.repository.PhotoRepository;

@RestController
public class GalleryController {
    private PhotoRepository photoRepository;

    @Autowired
    public GalleryController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/country/{name}/photos")
    public @ResponseBody
    List<Photo> getAllPublicPhotos(@PathVariable String name) {
        return photoRepository.findAllPublicPhotosForUnauthorized(name);
    }

    @GetMapping("/country/{name}/photos/{login}")
    public @ResponseBody
    List<Photo> getAllPhotosForAuthorized(@PathVariable String name, @PathVariable String login) {
        return photoRepository.findAllPublicPhotosForAuthorized(name, login);
    }

    @GetMapping("/country/{name}/photos/private")
    public @ResponseBody
    List<Photo> getAllUsersPhotos(@PathVariable String name, @RequestParam("user") String userLogin) {
        return photoRepository.findAllUsersPhotosInCountry(name, userLogin);
    }


}
