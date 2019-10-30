package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Description;

public interface DescriptionService {

    Description getDescriptionByCountryName(String name);
}