package com.sirmam.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.sirmam.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers")
@CrossOrigin
public class LotteryController {
	
	@Autowired
	private LotteryService lotteryService;
	
	@Value("${server.port}")
	private int port;
	
	// http://localhost:7700/lottery/api/v1/numbers
	@GetMapping
	public List<Integer> getNumbers(){
		System.err.println("New has request has arrived on port :" + port);
		return lotteryService.draw();
	}
}
