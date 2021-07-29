package com.sirmam.event;

public class TradeEvent {
	private String symbol;
	private double price;
	private double quantity;
	public TradeEvent(String symbol, double price, double quantity) {
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getPrice() {
		return price;
	}
	public double getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return "TradeEvent [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
