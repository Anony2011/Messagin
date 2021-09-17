package com.wissam.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wissam.messaging.model.Message;
import com.wissam.messaging.model.MessageRepository;
import com.wissam.messaging.model.User;
import com.wissam.messaging.model.UserRepository;

@SpringBootApplication
@EnableScheduling
public class MessagingApplication {
	@Autowired	
	private MessageRepository repository;
	@Autowired	
	private UserRepository urepository;	

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {

			User user1  = new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER");
			User user2  = new  User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN");
					
					
			urepository.save(user1);
			urepository.save(user2);
			
			repository.save(new Message("test","8cbec1d6-df1b-4b87-8b4c-06fc56f3efcc", user1));
			repository.save(new Message("asdasdsd", "8bbec1d6-df1b-4b87-8b4c-06fc56f3efcc",  user2));
			repository.save(new Message("asdasdasd", "8cbec1d6-af1b-4b87-8b4c-06fc56f3efcc",  user2));			

			
		};
	}	
}
