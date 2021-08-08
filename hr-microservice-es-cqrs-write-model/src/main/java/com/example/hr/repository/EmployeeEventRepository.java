package com.example.hr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hr.eventsource.EmployeeEvent;

public interface EmployeeEventRepository extends MongoRepository<EmployeeEvent, String> {

	boolean existsByIdentity(String identity);
	Optional<EmployeeEvent> findByIdentity(String identity);
	List<EmployeeEvent> findAllByConversationId(String conversationId);
	Optional<EmployeeEvent> findByConversationIdAndIdentity(String conversationId,String identity);

}
