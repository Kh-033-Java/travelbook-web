package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
