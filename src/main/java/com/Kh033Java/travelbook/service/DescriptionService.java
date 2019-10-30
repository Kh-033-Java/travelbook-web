package com.Kh033Java.travelbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.DescriptionRepository;

@Service
public class DescriptionService {
	
    @Autowired
    DescriptionRepository descriptionRepository;

    public Description getDesccriptionByCountryName(String name) {
        return descriptionRepository.getDesccriptionByCountryName(name);
    }

}
