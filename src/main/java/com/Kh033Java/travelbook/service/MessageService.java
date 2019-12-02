package com.Kh033Java.travelbook.service;

import java.util.List;

import com.Kh033Java.travelbook.entity.Message;
import com.Kh033Java.travelbook.entity.User;

/**
 * @author Anatolii Melchenko
 *
 */
public interface MessageService {
	
	String sendMessage(String sender, String receiver, Message message);

	List<User> getUserIntercolutors(String login);

	String getCorrespondenceBetweenUsers(String login, String interlocutor);

}
