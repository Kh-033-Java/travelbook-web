package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GalleryController {

    private static final Logger LOG = LoggerFactory.getLogger(GalleryController.class);


    private final PhotoService photoService;

    public GalleryController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/country/{name}/photos")
    public @ResponseBody
    List<Photo> getAllPublicPhotos(@PathVariable String name) {
        LOG.info("get photo from country {}", name);
        return photoService.findAllPublicPhotosForUnauthorized(name);
    }

    @GetMapping("/country/{name}/photos/{login}")
    public @ResponseBody
    List<Photo> getAllPhotosForAuthorized(@PathVariable String name, @PathVariable String login) {
        LOG.info("get photo from country {} by user {}", name, login);
        return photoService.findAllPublicPhotosForAuthorized(name, login);
    }

    @GetMapping("/country/{name}/photos/private")
    public @ResponseBody
    List<Photo> getAllUsersPhotos(@PathVariable String name, @RequestParam("user") String userLogin) {
        return photoService.findAllUsersPhotosInCountry(name, userLogin);
    }


}
