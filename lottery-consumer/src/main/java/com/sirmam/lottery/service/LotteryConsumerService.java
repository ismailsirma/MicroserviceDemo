package com.sirmam.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	private List<ServiceInstance> instances;
	
	// order
	private AtomicInteger counter = new AtomicInteger();
	
	@PostConstruct
	public void init() {
		instances = discoveryClient.getInstances("lottery-v1"); // spring.application.name
		instances.forEach(System.err::println);
	}
	
	// get consumer.period from application.properties
	//@Scheduled(fixedRateString  = "${consumer.period}")
	public void consumerLotteryService() {
		var restTemplate = new RestTemplate();
		var index = counter.getAndIncrement() % instances.size();
		var instance = instances.get(index);
		var url = String.format("http://%s:%d/lottery/api/v1/numbers", instance.getHost(), instance.getPort());
		System.err.println("Calling the service : " + url);
		var response = restTemplate.getForEntity(url, String.class).getBody();
		System.err.println(response);
	} 	
}
