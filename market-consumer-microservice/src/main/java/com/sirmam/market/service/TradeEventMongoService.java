package com.sirmam.market.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.sirmam.market.document.TickerDocument;
import com.sirmam.market.events.TradeEvent;
import com.sirmam.market.repository.TickerDocumentRepository;

@Service
public class TradeEventMongoService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TickerDocumentRepository ticherDocRepo;
	
	@EventListener
	public void listenTradeEvents(TradeEvent event) {
		var ticker = modelMapper.map(event, TickerDocument.class);
		ticherDocRepo.save(ticker);
	}	
}
