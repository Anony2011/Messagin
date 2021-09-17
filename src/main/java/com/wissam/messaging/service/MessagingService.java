package com.wissam.messaging.service;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissam.messaging.model.Message;
import com.wissam.messaging.model.MessageRepository;
import com.wissam.messaging.model.User;
import com.wissam.messaging.model.UserRepository;

@Service
public class MessagingService  {
  @Autowired
  private MessageRepository crepository;
  @Autowired
  private UserRepository urepository;

  final Timestamp sevenDaysAgo = new Timestamp(System.currentTimeMillis() - (7* 24* 60* 60 * 1000));
  
    public String newMessage(String message, String username)
    { 
      User currentUser = urepository.findByUsername(username);
      String token = UUID.randomUUID().toString();
      Message c = new Message(message,token,currentUser);
      crepository.save(c);
      return token;
    }

    public String readMessage(String token)
    { 
     List<Message> lm = crepository.findByUserDate(token, sevenDaysAgo);
     if (lm.size()>0)
    	 return lm.get(0).getMessage();
     else return "";
    }

    public void cleanUp()
    { 
    	crepository.deleteByDate(sevenDaysAgo);
    }


}

