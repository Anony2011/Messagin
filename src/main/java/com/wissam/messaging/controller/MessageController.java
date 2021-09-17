package com.wissam.messaging.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.wissam.messaging.model.Message;
import com.wissam.messaging.model.MessageRepository;
import com.wissam.messaging.model.UserRepository;
import com.wissam.messaging.service.MessagingService;
import com.wissam.messaging.util.UUID;

@Validated
@RestController
public class MessageController {
	@Autowired
	private MessageRepository crepository;
	@Autowired
	private UserRepository urepository;
	@Autowired
	private MessagingService ms;
	
	//unsafe code .. Key should be fetched from a key vault
	  public static String signingKey;
	  @Value("${jwt.key}")
	  public void setNameStatic(String k) throws NoSuchAlgorithmException{
		    MessageDigest md = MessageDigest.getInstance("SHA-512");
		    signingKey= java.util.Base64.getEncoder().encodeToString(md.digest(k.getBytes()));
	  }
	  
	@GetMapping("/messages")
	public  Iterable<Message> getMessages (Authentication authentication) {
		return crepository.findByUser(urepository.findByUsername(authentication.getName()));
	}
	@GetMapping("/message/{token}")
	public  String readMessage(@PathVariable("token") @UUID String token){

		String msg =  ms.readMessage(token);
		return msg;
		
	}
	
	@PostMapping("/message")  
	public  String saveMessage(@RequestBody @Size(max = 2000, message="Message size >2000")
	String msg, Authentication authentication) {
		return ms.newMessage(HtmlUtils.htmlEscape(msg),authentication.getName());
	}
}


  