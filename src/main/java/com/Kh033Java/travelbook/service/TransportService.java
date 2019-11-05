package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Transport;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.TransportRepository;

@Service
public interface TransportService {

    public List<Transport> getAllTransport();

}
