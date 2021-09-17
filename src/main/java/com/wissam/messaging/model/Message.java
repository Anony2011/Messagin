package com.wissam.messaging.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String message;
	 private String url; 
	
	@Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	 private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")	
	 private User user;


	public Message(String message, String token, User currentUser) {
		this.setMessage(message);
		this.setUrl(token); 
		this.setUser(currentUser);
	}

	
}
