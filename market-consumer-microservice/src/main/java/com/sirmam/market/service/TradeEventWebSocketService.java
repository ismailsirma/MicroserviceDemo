package com.sirmam.market.service;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

//import com.sirmam.market.document.TickerDocument;
import com.sirmam.market.events.TradeEvent;

@Service
public class TradeEventWebSocketService {
	
	//@Autowired
	//private ModelMapper modelMapper;
	
	//
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@EventListener
	public void listenTradeEvents(TradeEvent event) {
		//var ticker = modelMapper.map(event, TickerDocument.class);
		messagingTemplate.convertAndSend("/topic/changes", event);
	}	
}
