package com.Kh033Java.travelbook.service;

import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Description;

@Service
public interface DescriptionService {

    public Description getDescriptionByCountryName(String name);

}

