package com.Kh033Java.travelbook.entity;

import java.util.Date;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateString;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
@NodeEntity
public class Message {
	
	@Id
    @GeneratedValue
    private Long id;

    private String text;

	@DateString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date sendingTime;
    
    private boolean isRead;
    
    private String sender;
    
    public Message() {
    	
    }    

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(Date sendingTime) {
		this.sendingTime = sendingTime;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
