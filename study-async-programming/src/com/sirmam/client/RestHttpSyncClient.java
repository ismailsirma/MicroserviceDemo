package com.sirmam.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestHttpSyncClient {
	
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				.uri(URI.create(URL))
				.header("accept","application/json")
				.build();
		
		var start = System.currentTimeMillis();
		for(int i = 0; i <10 ;i++)
		{
			// blocking send method 
			var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();	
			System.err.println(response);
		}
		var stop = System.currentTimeMillis();
		
		System.err.println("Avg Duration: " + (stop - start)/10 + " msec.");
	}
}
