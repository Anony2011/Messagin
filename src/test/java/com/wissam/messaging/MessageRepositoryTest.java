package com.wissam.messaging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissam.messaging.model.Message;
import com.wissam.messaging.model.MessageRepository;
import com.wissam.messaging.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private MessageRepository mrepository;
		
	@Test
	public void saveMessage() {
		User u  = new User("testing", "whatever", "USER");
		Message m = new Message("asdasdas","asdasdasdasd",u);
				
		entityManager.persistAndFlush(u);
		entityManager.persistAndFlush(m);
		assertThat(m.getId()).isNotNull();
	}
	
	@Test
	public void deleteMessage() {
		User u1  = new User("testing", "whatever", "USER");
		entityManager.persistAndFlush(u1);

		entityManager.persistAndFlush(new Message("testst", "asdasdasdasd",u1));
		entityManager.persistAndFlush(new Message("asdasdsadasd", "aaaaa",u1));		
		
		mrepository.deleteAll();

		assertThat(mrepository.findAll()).isEmpty();
	}	

}
