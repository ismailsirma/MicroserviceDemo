package com.sirmam.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
public class StandardLotteryService implements LotteryService {
		
	@Override
	public List<Integer> draw() {
		
		return ThreadLocalRandom.current().ints(1,60).distinct().limit(6).sorted().boxed()
				.collect(Collectors.toList());
	}

}
