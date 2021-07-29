package com.sirmam.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RestHttpAsyncClient {
	
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				.uri(URI.create(URL))
				.header("accept","application/json")
				.build();
		
		var start = System.currentTimeMillis();
		for(int i = 0; i <10 ;i++)
		{
			// non-blocking send method 
			// CompatebleFuture<HttpResponse> cevap doner
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenAcceptAsync(response -> {
					System.err.println(Thread.currentThread().getName() + ": " + response.body());
					if(counter.incrementAndGet()>=10) {
						var stop = System.currentTimeMillis();
						System.err.println("Duration: " + (stop - start) + " msec.");
					}
				}, Executors.newFixedThreadPool(32));
			
		}
		
		System.err.println("All requests are sent!");
		TimeUnit.SECONDS.sleep(10);
		System.err.println("Done!");
	}
}
