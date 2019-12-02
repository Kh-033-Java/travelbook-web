package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Message;
import com.Kh033Java.travelbook.entity.User;
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
	
    @PostMapping(value = "messages/sendMessage")
    public String sendMessageFromUserToUser(@RequestParam String sender, @RequestParam String receiver, @RequestBody Message message) {
    	return messageService.sendMessage(sender, receiver, message);
    }
    
    @GetMapping(value = "messages/{login}/contacts")
    public List<User> getUserConversations(@PathVariable String login) {
    	return messageService.getUserIntercolutors(login);
    }
    
    @GetMapping(value = "messages/{login}/history/{interlocutor}")
    public String getMessageHistory(@PathVariable String login, @PathVariable String interlocutor) {
    	return messageService.getCorrespondenceBetweenUsers(login, interlocutor);
    }

}
