package com.sirmam.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LotteryAsyncService {
	public CompletableFuture<List<Integer>> asyncDraw(int max, int size){
		// draw function returns immediately
		// supplyasync runs in the background
		return CompletableFuture.supplyAsync( () -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return ThreadLocalRandom.current().ints(1, max)
					.distinct()
					.limit(size)
					.sorted()
					.boxed()
					.collect(Collectors.toList());
		});
				

	}
}
