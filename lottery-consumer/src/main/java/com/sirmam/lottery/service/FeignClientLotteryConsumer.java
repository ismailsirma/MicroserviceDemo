package com.sirmam.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sirmam.lottery.service.client.LotteryService;

@Service
public class FeignClientLotteryConsumer {

	@Autowired
	private LotteryService lotteryService;
	
	@Scheduled(fixedRateString  = "${consumer.period}")
	public void consumerLotteryService() {
		System.err.println(lotteryService.draw()); 
	}
}
