package com.example.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.entity.Employee;

@Service
public class EmployeeWebSocketService {
	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@EventListener
	public void listenNewEmployees(Employee emp) {
		messageTemplate.convertAndSend(
				"/topic/changes",emp);
	}
}
