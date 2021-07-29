package com.sirmam.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class WebsocketClient {

	private static final String URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";
	
	public static void main(String[] args) throws InterruptedException {
		HttpClient.newHttpClient()
				.newWebSocketBuilder()
				.buildAsync(URI.create(URL), new BinanceWebSocketListener());
		
		// allow application to be running asynchronously one minute
		TimeUnit.MINUTES.sleep(1);
	}
}

// Reactive Listener
class BinanceWebSocketListener implements Listener{

	@Override
	public void onOpen(WebSocket webSocket) {
		Listener.super.onOpen(webSocket);
		System.err.println("Connected!");
		// for subscriber to process request one after the other
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		
		System.err.println(data);
		//return Listener.super.onText(webSocket, data, last);
		
		// for subscriber to process request one after the other 
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Connection is closed!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		Listener.super.onError(webSocket, error);
		System.err.println("Error: " + error.getMessage());
	}
	
}