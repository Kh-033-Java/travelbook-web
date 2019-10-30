package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Transport;
import com.Kh033Java.travelbook.service.TransportService;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
@RestController
public class TransportController {
	
	@Autowired
	TransportService transportService;
	
	@RequestMapping(value = "/transport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transport> getTransport() {
		return transportService.getAllTransport();
	}

}
