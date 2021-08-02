package com.sirmam.lottery.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "lottery-v1") // 2nd parameter : fallback = ""
public interface LotteryService {
	// different instances may have different host and port
	@GetMapping("/lottery/api/v1/numbers")
	List<Integer> draw();
}
