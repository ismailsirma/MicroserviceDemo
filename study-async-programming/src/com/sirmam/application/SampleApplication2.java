package com.sirmam.application;

import java.util.concurrent.TimeUnit;

import com.sirmam.service.LotteryAsyncService;

public class SampleApplication2 {

	public static void main(String[] args) throws InterruptedException {
		var lotteryService = new LotteryAsyncService();
		System.err.println("Just started...");
		lotteryService.asyncDraw(60,6)
					.thenAccept( numbers -> System.err.println(Thread.currentThread().getName() + ": " + numbers));
		for(var i = 0; i < 10 ;++i)
			System.err.println("i: " + i);
		TimeUnit.SECONDS.sleep(10);
	}

}
