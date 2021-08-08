package com.sirmam.service;

import java.net.URI;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sirmam.dto.Ticker;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class BinanceClientService {
	
	private Map<String, Ticker> tickerCache = new WeakHashMap<>();
	
	@Retry(name = "binance", fallbackMethod = "fallbackGetTickerBySymbol")
	@RateLimiter(name = "binance", fallbackMethod = "fallbackGetTickerBySymbol")
	public Ticker getTickerBySymbol(String symbol) {
		RestTemplate rt = new RestTemplate();
		var ticker = rt.getForEntity(URI.create("https://api.binance.com/api/v3/ticker/price?symbol="+ symbol), Ticker.class).getBody();
		tickerCache.put(symbol, ticker);
		return ticker;
	}
	
	public Ticker fallbackGetTickerBySymbol(String symbol) {
		return tickerCache.get(symbol);
	}
}
