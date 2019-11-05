package com.Kh033Java.travelbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.DescriptionRepository;

@Service
public interface DescriptionService {

    public Description getDescriptionByCountryName(String name);

}
