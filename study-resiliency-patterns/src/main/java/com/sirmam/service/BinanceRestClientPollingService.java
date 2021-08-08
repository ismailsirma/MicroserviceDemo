package com.sirmam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BinanceRestClientPollingService {

	@Autowired
	private BinanceClientService binanceClientService;
	
	public void callBinance() {
		System.err.println(binanceClientService.getTickerBySymbol("BTCUSDT"));
		System.err.println(binanceClientService.getTickerBySymbol("EOSETH"));
	}
}
