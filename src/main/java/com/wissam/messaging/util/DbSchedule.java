package com.wissam.messaging.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wissam.messaging.service.MessagingService;

@Component
public class DbSchedule {
    Logger logger = LoggerFactory.getLogger(DbSchedule.class);
    @Autowired 
    MessagingService ms;
    @Scheduled(fixedRate = 60*60*1000)
    public void cleanDB() {
        logger.info("cleaning");
       ms.cleanUp();
    }
}
