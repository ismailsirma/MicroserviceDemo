package com.sirmam.market.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitTradeListenerService {

		@RabbitListener(queues = "trades")
		public void listenTrade(String trade) {
			System.err.println("Received a new trade from RabbitMQ: " + trade);
		}
}
