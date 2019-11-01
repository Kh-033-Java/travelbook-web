package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.service.DescriptionService;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.DescriptionRepository;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    public Description getDescriptionByCountryName(String name) {
        return descriptionRepository.getDescriptionByCountryName(name);
    }

}
