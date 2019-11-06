package com.Kh033Java.travelbook.service.impl;

import java.util.List;

import com.Kh033Java.travelbook.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Transport;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.TransportRepository;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransport() {
        return transportRepository.findAll();
    }

}
