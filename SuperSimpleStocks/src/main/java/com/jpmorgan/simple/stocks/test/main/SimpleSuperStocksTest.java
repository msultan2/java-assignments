package com.jpmorgan.simple.stocks.test.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.simple.stocks.model.Stock;
import com.jpmorgan.simple.stocks.model.StockManager;
import com.jpmorgan.simple.stocks.model.StockType;
import com.jpmorgan.simple.stocks.model.TradeType;
import com.jpmorgan.simple.stocks.model.stockException;

/**
 * 
 * @author msultan
 *
 */
public class SimpleSuperStocksTest {
	private StockManager stockManager = new StockManager();
	private Integer marketPrice = 0;
	private String[] stockSymbols;
	private int sleepTime; // wait for 1 sec
	private int randomMax;
	private Double fixedDividend;
	private String TestStockSymboleTest;
	private String TestStockSymboleTest2;
	private int stockPriceRecordAge;
	private int passedTradeMinute;
	private int secondsPerMinutes;
	private int milliSecondsInSecond;
	private Double stockPrice1;
	private Double stockPrice2;
	private Double stockPrice3;
	int stockQuantityTest1;
	int stockQuantityTest2;
	int stockQuantityTest3;
	Random random;

	@Before
	public void before() {
		marketPrice = 0;
		sleepTime = 1000; // wait for 1 sec
		randomMax = 100;
		fixedDividend = 0.2;
		TestStockSymboleTest = "ALE";
		TestStockSymboleTest2 = "GIN";
		stockPriceRecordAge = 15;
		passedTradeMinute = stockPriceRecordAge + 1;
		secondsPerMinutes = 60;
		milliSecondsInSecond = 1000;

		stockPrice1 = 10.0; // assume that stockPrice=10
		stockPrice2 = 4.0; // assume that stockPrice for second stock=4
		stockPrice3 = 1.0; // assume that stockPrice for third stock=1
		stockQuantityTest1 = 2; // assume that stock quantity=2
		stockQuantityTest2 = 3; // assume that stock quantity=3 for the second
								// stock
		stockQuantityTest3 = 5; // assume that stock quantity=5 for the third
								// stock
		random = new Random();
		marketPrice = random.nextInt(randomMax); // for testing only

		stockSymbols = stockManager.getStockSymbols();
	}

	// Testing Dividend Yield output for COMMON and Preferred
	@Test
	public void dividendYieldTest() throws stockException {
		for (String stockSymbol : stockSymbols) {
			Stock stock = stockManager.getStocks().get(stockSymbol);
			if (stock.getStockType() == StockType.COMMON)
				assertTrue(stock.calcDividendYield(marketPrice * 1.0) == stock.getLastDividend() / marketPrice);
			else
				assertTrue(stock.calcDividendYield(marketPrice * 1.0) == fixedDividend * stock.getParValue() / marketPrice);
		}
	}

	// Testing P/E Ratio output assuring that the record is inserterd and
	@Test
	public void pE_RatioTest() throws stockException {

		for (String stockSymbol : stockSymbols) {

			Stock stock = stockManager.getStocks().get(stockSymbol);
			stock.setMarketPrice(marketPrice * 1.0);
			assertTrue(stock.calcPERatio() == stock.getMarketPrice() / stock.calcDividendYield(stock.getMarketPrice()));
		}
	}

	// Recording a few trades
	@Test
	public void recordTradeTest() throws InterruptedException {

		// Since Trade is not filled yet, number of trades = zero
		assertEquals(0, stockManager.getTradesCount());
		int step = 0;
		for (String stockSymbol : stockSymbols) {
			step++;

			stockManager.recordTrade(stockSymbol, stockQuantityTest1, stockPrice1, TradeType.BUY);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbol, stockQuantityTest2, stockPrice2, TradeType.BUY);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbol, stockQuantityTest1, stockPrice1, TradeType.SELL);
			Thread.sleep(sleepTime);

			assertEquals(stockManager.getTrades().size(), 3 * step);
		}
	}

	// Testing Stock Price based on trades recorded in past 15 minutes
	// checking that record more than 15 minutes are eliminated
	@Test
	public void stockPriceTest() throws InterruptedException, stockException {

		stockManager.recordTrade(TestStockSymboleTest, stockQuantityTest1, stockPrice1, TradeType.BUY);
		Thread.sleep(sleepTime);
		stockManager.recordTrade(TestStockSymboleTest, stockQuantityTest2, stockPrice2, TradeType.SELL);
		Thread.sleep(sleepTime);
		stockManager.recordTrade(TestStockSymboleTest, stockQuantityTest3, stockPrice3, TradeType.BUY);
		Thread.sleep(sleepTime);
		stockManager.recordTrade(
				new Date(new Date().getTime() - (passedTradeMinute * secondsPerMinutes * milliSecondsInSecond)),
				TestStockSymboleTest, stockQuantityTest1, stockPrice2, TradeType.BUY);
		Double expected = 3.7;	//This value is manually caculated to assure the proper system calcualtions
		assertEquals(expected, stockManager.getStockPrice(TestStockSymboleTest,
				stockPriceRecordAge * secondsPerMinutes * milliSecondsInSecond));
	}

	// Calculates the GBCE All Share Index using the geometric mean of prices
	// for all stocks
	@Test
	public void allShareIndexTest() throws InterruptedException, stockException {
		stockManager.clearTrades();
		for (String stockSymbol : stockSymbols) {
			stockManager.recordTrade(stockSymbol, stockQuantityTest1, stockPrice1, TradeType.BUY);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbol, stockQuantityTest2, stockPrice2, TradeType.BUY);
			Thread.sleep(sleepTime);
			stockManager.recordTrade(stockSymbol, stockQuantityTest3, stockPrice3, TradeType.SELL);
			Thread.sleep(sleepTime);
		}
		Double actual = stockManager.calcAllShareIndex();
		Double expected = 3.7; //This value is manually caculated to assure the proper system calcualtions
		assertEquals(expected, actual);

		// stockManager.clearTrades();
		stockManager.recordTrade(TestStockSymboleTest, stockQuantityTest1, stockPrice1, TradeType.BUY);
		Thread.sleep(sleepTime);
		//Testing another expected value
		stockManager.recordTrade(TestStockSymboleTest2, stockQuantityTest3, stockPrice3, TradeType.SELL);
		DecimalFormat df = new DecimalFormat("#.##");
		actual = Double.valueOf(df.format(stockManager.calcAllShareIndex())); //rounded data to be ready for assertion
		expected = 3.68; //This value is manually caculated to assure the proper system calcualtions
		assertEquals(expected, actual);
	}
}
