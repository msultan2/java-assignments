package com.jpmorgan.simple.stocks.main;

import com.jpmorgan.simple.stocks.model.StockManager;
import com.jpmorgan.simple.stocks.model.TradeType;
import com.jpmorgan.simple.stocks.model.stockException;

/**
 * 
 * @author msultan
 * 
 */

public class SimpleSuperStocks {
	// I added the main for general purpose only. however the JUnit test will be enough for test assurance.
	public static void main(String[] args) throws stockException {
		String stockSymbolTest1 = "ALE";
		String stockSymbolTest2 = "GIN";
		int stockPriceRecordAge = 15;
		int secondsPerMinutes = 60;
		int milliSecondsInSecond = 1000;

		Double stockPrice1 = 0.8; // assume that stockPrice=0.8
		Double stockPrice2 = 0.3; // assume that stockPrice for second stock=0.3
		int stockQuantityTest1 = 5; // assume that stock quantity=5
		int stockQuantityTest2 = 7; // assume that stock quantity=7 for the
									// second stock
		int sleepTime = 1000; // Default value to waiting time 1 Sec

		System.out.println("Filling stockManager with Stocks");
		StockManager stockManager = new StockManager();

		// Calculating dividend yield for test stock symbol
		Double dividendYield = stockManager.getStocks().get(stockSymbolTest1).calcDividendYield(stockPrice1);
		System.out.println("Dividend yield for " + stockSymbolTest1 + " = " + dividendYield);

		// Calculating P/E ratio for test stock symbol, assuming stockPrice1
		stockManager.getStocks().get(stockSymbolTest1).setMarketPrice(stockPrice1);
		Double peRatio = stockManager.getStocks().get(stockSymbolTest1).calcPERatio();
		System.out.println("P/E Ratio output for " + stockSymbolTest1 + " = " + peRatio);

		// Recording a few sample trades for 2 stock symbols for testing
		try {
			stockManager.recordTrade(stockSymbolTest1, stockQuantityTest1, stockPrice1, TradeType.BUY);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbolTest1, stockQuantityTest1, stockPrice1, TradeType.SELL);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbolTest2, stockQuantityTest2, stockPrice2, TradeType.BUY);
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.out.println("Error in Sleep:" + e.getMessage());
		}
		// Calculating Stock Price based on trades recorded in past 15 minutes";
		Double stockPrice = stockManager.getStockPrice(stockSymbolTest1,
				stockPriceRecordAge * secondsPerMinutes * milliSecondsInSecond);
		System.out.println(
				"Stock Price for " + stockSymbolTest1 + " based on trades recorded in past 15 minutes=" + stockPrice);
		System.out.println("Performing another trade for " + stockSymbolTest2);
		stockManager.recordTrade(stockSymbolTest2, stockQuantityTest2, stockPrice2, TradeType.SELL);

		// Calculating GBCE for all stocks
		Double allShareIndex = stockManager.calcAllShareIndex();
		System.out.println("The GBCE All Share Index for all Stocks is:" + allShareIndex);

	}
}
