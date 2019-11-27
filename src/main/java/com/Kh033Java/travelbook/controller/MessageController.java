package com.Kh033Java.travelbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Message;
import com.Kh033Java.travelbook.service.MessageService;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
@RestController
public class MessageController {
	
	private final MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
    @PostMapping(value = "/sendMessage")
    public void sendMessageFromUserToUser(@RequestParam String sender, @RequestParam String receiver, @RequestBody Message message) {
    	messageService.sendMessage(sender, receiver, message);
    }

}
