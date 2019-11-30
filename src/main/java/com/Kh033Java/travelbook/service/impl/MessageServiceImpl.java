package com.Kh033Java.travelbook.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kh033Java.travelbook.entity.Message;
import com.Kh033Java.travelbook.repository.MessageRepository;
import com.Kh033Java.travelbook.service.MessageService;

/**
 * @author Anatolii Melchenko
 *
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	private final MessageRepository messageRepository;
	private static final String SENDED_SUCCESSFUL = "Message sended, text = ";

	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	// TODO handle if don't send successfully
	@Override
	public String sendMessage(String sender, String receiver, Message message) {
//		messageRepository.saveMessage();
		return SENDED_SUCCESSFUL + message.getText();
	}
}
