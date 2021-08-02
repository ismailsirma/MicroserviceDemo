package com.sirmam.lottery.service.fallback;

import java.util.List;

import com.sirmam.lottery.service.client.LotteryService;

public class LotteryServiceFallback implements LotteryService {

	@Override
	public List<Integer> draw() {
		return List.of(1,2,3,4,5,6);
	}

}
