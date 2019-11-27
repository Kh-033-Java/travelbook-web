package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Message;

/**
 * @author Anatolii Melchenko
 *
 */
public interface MessageService {
	
	String sendMessage(String sender, String receiver, Message message);

}
