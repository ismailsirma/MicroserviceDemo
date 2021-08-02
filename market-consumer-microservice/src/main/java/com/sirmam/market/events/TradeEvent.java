package com.sirmam.market.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeEvent {

	@JsonProperty("s")
	private String symbol;
	
	@JsonProperty("p")
	private String price;
	
	@JsonProperty("q")
	private String quantity;

	public TradeEvent() {
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
	}
}
