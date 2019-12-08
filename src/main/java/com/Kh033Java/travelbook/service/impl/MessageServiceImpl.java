package com.Kh033Java.travelbook.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		message.setSender(sender);
		messageRepository.save(message);
		messageRepository.creatRelationshipBetweenSenderAndMessage(sender, message.getId());
		messageRepository.creatRelationshipBetweenReceiverAndMessage(receiver, message.getId());
		return SENDED_SUCCESSFUL + message.getText();
	}

	@Override
	public Set<User> getUserIntercolutors(String login) {
		List<User> receivers = userRepository.getThoseToWhomUserSentMessage(login);
		List<User> senders = userRepository.getThoseFromWhichUserReceivedMessage(login);
		Set<User> intercolutors = new HashSet<>();
		intercolutors.addAll(receivers);
		intercolutors.addAll(senders);
		for (User user : intercolutors) {
			user.setAvatar(photoRepository.findUserAvatarByUserId(user.getLogin()));
		}
		return intercolutors;
	}

	@Override
	public String getCorrespondenceBetweenUsers(String login, String interlocutor) {
		List<Message> sendedMessages = messageRepository.getUserSendedMessages(login, interlocutor);
		List<Message> receivedMessages = messageRepository.getUserReceivedMessages(login, interlocutor);
		Collections.reverse(sendedMessages);
		Collections.reverse(receivedMessages);
		List<Message> correspondence = new ArrayList<>();
		correspondence.addAll(sendedMessages);
		correspondence.addAll(receivedMessages);
		Comparator<Message> messageComparator = Comparator.comparing(Message::getSendingTime);
		correspondence.sort(messageComparator);
		return JsonConverter.convertToJson(correspondence);
	}	
	
}
