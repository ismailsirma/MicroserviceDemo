package com.sirmam.market.dto;

public class Ticker {
	private String symbol;
	private String price;
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
		return "Ticker [symbol=" + symbol + ", price=" + price + "]";
	}
	public Ticker() {
	}
	
}
