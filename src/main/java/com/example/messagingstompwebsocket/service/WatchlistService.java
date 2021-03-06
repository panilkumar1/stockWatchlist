package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.controller.WatchlistController;
import com.example.messagingstompwebsocket.model.StockDto;
import com.example.messagingstompwebsocket.model.WatchlistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WatchlistService {
	private Set<String> stockSymbols = new HashSet<>();
	@Autowired
	private WatchlistController watchlistController;
	@Autowired
	private StockService stockService;

	public void addStock(String symbol) {
		stockSymbols.add(symbol);
	}

	public void removeStock(String symbol) {
		stockSymbols.remove(symbol);
	}

	@Scheduled(fixedDelay = 10000)
	public void PushStockPrices() {
		watchlistController.pushWatchlist(getWatchlistDto());
	}

	public WatchlistDto getWatchlistDto() {
		WatchlistDto watchlist = new WatchlistDto();
		for (String stockSymbol : stockSymbols) {
			StockDto stockDto = stockService.getStock(stockSymbol);
			if (stockDto != null){
				watchlist.addStock(stockDto);
			}
		}

		return watchlist;
	}
}