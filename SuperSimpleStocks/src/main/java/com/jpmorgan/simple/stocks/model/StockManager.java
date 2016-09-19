package com.jpmorgan.simple.stocks.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Manages Stocks trasactions including recording trade and calculating All Share Index 
 * 
 * @author msultan
 *
 */
public class StockManager {

	private HashMap<String, Stock> stocks;
	private TreeMap<Date, Trade> trades;
	private String[] stockSymbols = { "TEA", "POP", "ALE", "GIN", "JOE" };

	public String[] getStockSymbols() {
		return stockSymbols;
	}

	public void setStockSymbols(String[] stockSymbols) {
		this.stockSymbols = stockSymbols;
	}

	public StockManager() {
		trades = new TreeMap<Date, Trade>();
		stocks = new HashMap<String, Stock>();

		ApplicationContext context = new ClassPathXmlApplicationContext("simpleSuperStocksSpring.xml");
		stocks.clear();
		for (String stockSymbol : stockSymbols) {
			stocks.put(stockSymbol, (Stock) context.getBean(stockSymbol.toLowerCase()));
		}
	}

	public HashMap<String, Stock> getStocks() {
		return stocks;
	}

	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}

	public TreeMap<Date, Trade> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<Date, Trade> trades) {
		this.trades = trades;
	}

	public int getTradesCount() {
		return trades.size();
	}

	public SortedMap<Date, Trade> getLast_X_MinTrades(int minutesAgo) {
		Date now = new Date();
		Date lastXMinAgo = new Date(now.getTime() - (minutesAgo));
		SortedMap<Date, Trade> trimmedTrades = this.trades.tailMap(lastXMinAgo);
		return trimmedTrades;
	}

	public Double getStockPrice(String stockSymbol) {
		Integer totalQuantity = 0;
		Double allTradePrice = 0.0;
		Double stockPriceRet = 0.0;
		for (Trade trade : trades.values()) {
			if (trade.getStock().getStockSymbol().equalsIgnoreCase(stockSymbol)) {
				totalQuantity += trade.getSharesQuantity();
				allTradePrice += trade.getTradePrice() * trade.getSharesQuantity();
			}
		}
		if (totalQuantity != 0)
			stockPriceRet = allTradePrice / totalQuantity;
		return stockPriceRet;
	}

	public Double getStockPrice(String stockSymbol, int minutesAgo) throws stockException {
		SortedMap<Date, Trade> trimmedTrades = getLast_X_MinTrades(minutesAgo);

		System.out.println(trimmedTrades.size());

		Integer totalQuantity = 0;
		Double allTradePrice = 0.0;
		Double stockPrice = 0.0;
		for (Trade trade : trimmedTrades.values()) {
			if (trade.getStock().getStockSymbol().equalsIgnoreCase(stockSymbol)) {
				totalQuantity += trade.getSharesQuantity();
				allTradePrice += trade.getTradePrice() * trade.getSharesQuantity();
			}
		}
		if (totalQuantity != 0)
			stockPrice = allTradePrice / totalQuantity;
		else
			throw new stockException("Invalid StockType");
		return stockPrice;
	}

	public Double calcAllShareIndex() throws stockException {
		Double allStocksTimes = 1.0;
		Double stockPrice = 0.0;
		Double stockCount = 0.0;
		Double allShareIndex = 0.0;
		Double nthRoot;
		for (Stock stock : stocks.values()) {
			stockPrice = getStockPrice(stock.getStockSymbol());
			if (stockPrice != 0) {
				stockCount += 1;
				allStocksTimes *= stockPrice;
			}
		}
		if (stockCount > 0) {
			nthRoot = (1 / stockCount);
			allShareIndex = Math.pow(allStocksTimes, nthRoot);
		} else {
			throw new stockException("No stocks found, stock count = 0");
		}
		return allShareIndex;
	}

	// records a give trade in the current time stamp
	public void recordTrade(String stockSymbol, int quantity, double symbolPrice, TradeType tradeType) {
		this.trades.put(new Date(), new Trade(this.stocks.get(stockSymbol), tradeType, quantity, symbolPrice));
	}

	// overrided function, records a give trade for a give timestamp
	public void recordTrade(Date date, String stockSymbol, int quantity, double symbolPrice, TradeType tradeType) {
		this.trades.put(date, new Trade(this.stocks.get(stockSymbol), tradeType, quantity, symbolPrice));
	}

	public void clearTrades() {
		this.trades.clear();
	}
}