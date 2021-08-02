package com.sirmam.lottery.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sirmam.lottery.service.fallback.LotteryServiceFallback;

@FeignClient(name = "lottery-v1", fallback = LotteryServiceFallback.class) 
public interface LotteryService {
	// different instances may have different host and port
	@GetMapping("/lottery/api/v1/numbers")
	List<Integer> draw();
}
