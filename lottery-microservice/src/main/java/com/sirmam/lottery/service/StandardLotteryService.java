package com.sirmam.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StandardLotteryService implements LotteryService {
	
	@Value("${lottery.max}") // Application.properties içindeki adı SpELL : Spring Execution LANGUAGE 
	private int lotteryMax;
	@Value("${lottery.size}")
	private int lotterySize;
	
	@Override
	public List<Integer> draw() {
		
		return ThreadLocalRandom.current().ints(1,lotteryMax).distinct().limit(lotterySize).sorted().boxed()
				.collect(Collectors.toList());
	}

}
