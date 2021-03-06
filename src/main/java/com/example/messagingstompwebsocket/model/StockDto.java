package com.example.messagingstompwebsocket.model;

import java.math.BigDecimal;

public class StockDto {
	private String symbol;
	private BigDecimal price;

	public StockDto() {
	}

	public StockDto(String symbol, BigDecimal price) {
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
