package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.model.StockDto;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Component
public class StockService {

	public StockDto getStock(String symbol) {
		StockDto stockDto = null;
		try {
			Stock stock = YahooFinance.get(symbol);
			if (stock != null) {
				stockDto = new StockDto(stock.getSymbol(), stock.getQuote().getPrice());
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

		return stockDto;
	}
}