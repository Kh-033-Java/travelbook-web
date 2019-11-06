package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Transport;

@Service
public interface TransportService {

    public List<Transport> getAllTransport();

}
