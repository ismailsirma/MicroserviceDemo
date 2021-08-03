package com.sirmam.market.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirmam.market.dto.Trade;

@Service 
public class TradeRabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate; // message broker
	
	@Autowired
	private ObjectMapper mapper;
	
	// event that we want to subscribe to
	@EventListener
	public void listenTradeEvents(Trade trade) {
		System.err.println("Application event: " + trade);
		String json;
		try {
			json = mapper.writeValueAsString(trade);
			// first input is rabbit mq exchange name
			rabbitTemplate.convertAndSend("marketex", null, json);
		} catch (JsonProcessingException e) {
			System.err.println("Error in sending trade event: " + e.getMessage());
		}
	}
}
