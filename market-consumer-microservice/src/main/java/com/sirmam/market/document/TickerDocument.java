package com.sirmam.market.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickers")
public class TickerDocument {

	@Id	
	private String symbol;
	private String price;
	public TickerDocument() {
	}
	public TickerDocument(String symbol, String price) {
		this.symbol = symbol;
		this.price = price;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "TickerDocument [symbol=" + symbol + ", price=" + price + "]";
	}
	
	
	
}
