package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.model.WatchlistDto;
import com.example.messagingstompwebsocket.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WatchlistController {
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private WatchlistService watchlistService;

	@MessageMapping("/stocks/add")
	@SendTo("/topic/watchlist")
	public WatchlistDto addStock(String symbol) throws Exception {
		// TODO Anil: Validate if the symbol is correct. If not throw an error
		watchlistService.addStock(symbol);
		return watchlistService.getWatchlistDto();
	}

	@MessageMapping("/stocks/remove")
	public void removeStock(String symbol) throws Exception {
		watchlistService.removeStock(symbol);
	}

	public void pushWatchlist(WatchlistDto watchlist) {
		this.template.convertAndSend("/topic/watchlist", watchlist);
	}
}
