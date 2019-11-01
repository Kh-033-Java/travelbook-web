package com.Kh033Java.travelbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.DescriptionRepository;

@Service
public class DescriptionService {
	
    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
		this.descriptionRepository = descriptionRepository;
	}

	public Description getDescriptionByCountryName(String name) {
        return descriptionRepository.getDesccriptionByCountryName(name);
    }

}
