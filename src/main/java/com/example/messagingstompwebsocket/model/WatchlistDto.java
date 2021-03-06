package com.example.messagingstompwebsocket.model;

import java.util.ArrayList;
import java.util.List;

public class WatchlistDto {

	private List<StockDto> stocks = new ArrayList<>();

	public WatchlistDto() {

	}

	public WatchlistDto(List<StockDto> stocks) {
		this.stocks = stocks;
	}

	public List<StockDto> getStocks() {
		return stocks;
	}

	public void addStock(StockDto stock) {
		stocks.add(stock);
	}
}
