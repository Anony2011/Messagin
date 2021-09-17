package com.wissam.messaging.model;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
@Transactional
public interface MessageRepository extends CrudRepository <Message, Long> {

	List<Message> findByUser(@Param("user") User user);
	List<Message> findByUrl(@Param("url") String url);
	
	 @Query(value = "SELECT * FROM message WHERE timestamp >:ts AND url=:url", nativeQuery = true)
	 List<Message> findByUserDate(String url,Timestamp ts);
	
	 @Modifying      // to mark delete or update query
	 @Query(value = "DELETE FROM message WHERE timestamp < :ts", nativeQuery = true)    
	 int deleteByDate(@Param("ts") Timestamp ts);
}
