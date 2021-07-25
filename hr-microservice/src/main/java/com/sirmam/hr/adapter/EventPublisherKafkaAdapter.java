package com.sirmam.hr.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirmam.hr.dto.EmployeeKafkaEvent;
import com.sirmam.hr.events.EmployeeEvent;
import com.sirmam.hr.infrastructure.EventPublisher;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher {

	// Adding Anti-CorruptionLayer
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void publish(EmployeeEvent event) {
		var employeeKafkaEvent = modelMapper.map(event, EmployeeKafkaEvent.class);
		
		try {
			var jsonDocument = objectMapper.writeValueAsString(employeeKafkaEvent);			
			kafkaTemplate.send("hr", jsonDocument);
		} catch(Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

}
