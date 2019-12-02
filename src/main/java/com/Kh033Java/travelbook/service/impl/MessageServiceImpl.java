package com.Kh033Java.travelbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kh033Java.travelbook.dto.MessageDTO;
import com.Kh033Java.travelbook.entity.Message;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.repository.MessageRepository;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.service.MessageService;
import com.Kh033Java.travelbook.util.JsonConverter;

/**
 * @author Anatolii Melchenko
 *
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	private final PhotoRepository photoRepository;
	private static final String SENDED_SUCCESSFUL = "Message sended, text = ";

	public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, PhotoRepository photoRepository) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
		this.photoRepository = photoRepository;
	}

	// TODO handle if don't send successfully
	@Override
	public String sendMessage(String sender, String receiver, Message message) {
		message.setSendingTime(new Date());
		message.setIsRead(false);
		messageRepository.save(message);
		messageRepository.creatRelationshipBetweenSenderAndMessage(sender, message.getId());
		messageRepository.creatRelationshipBetweenReceiverAndMessage(receiver, message.getId());
		return SENDED_SUCCESSFUL + message.getText();
	}

	// TODO test on more users
	@Override
	public List<User> getUserIntercolutors(String login) {
		List<User> foundUsers = userRepository.getUserIntercolutors(login);
		for (User user : foundUsers) {
			user.setAvatar(photoRepository.findUserAvatarByUserId(user.getLogin()));
		}
		return foundUsers;
	}

	@Override
	public String getCorrespondenceBetweenUsers(String login, String interlocutor) {
		List<Message> sendedMessages = messageRepository.getUserSendedMessages(login, interlocutor);
		List<Message> receivedMessages = messageRepository.getUserReceivedMessages(login, interlocutor);
		MessageDTO messageDTO = new MessageDTO(sendedMessages, receivedMessages);
		return JsonConverter.convertToJson(messageDTO);
	}	
	
}
