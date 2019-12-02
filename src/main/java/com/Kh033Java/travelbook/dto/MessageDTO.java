package com.Kh033Java.travelbook.dto;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.Kh033Java.travelbook.entity.Message;

@ComponentScan
public class MessageDTO {
	
	private final List<Message> sendedMessages;
    private final List<Message> receivedMessages;
    
	public MessageDTO(List<Message> sendedMessages, List<Message> receivedMessages) {
		this.sendedMessages = sendedMessages;
		this.receivedMessages = receivedMessages;
	}    

}
