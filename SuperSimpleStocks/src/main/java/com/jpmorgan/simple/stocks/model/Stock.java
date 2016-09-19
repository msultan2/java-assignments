package com.jpmorgan.simple.stocks.model;

/**Represents each Stock including its parameters.
 * 
 * @author msultan
 * 
 */

public class Stock {
	private String stockSymbol;
	private StockType stockType;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private double marketPrice ;

	public Stock() {
		// Constructor
		// no initialization needed
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	// To calculate DividendYield based on stock type prefeered or common
	public double calcDividendYield() throws stockException {
		double dividendYield = 0.0;
		if (marketPrice > 0.0) {
			if (stockType == StockType.COMMON) {
				dividendYield = lastDividend / marketPrice;
			} else if (stockType == StockType.PREFERRED) {
				// PREFERRED
				dividendYield = (fixedDividend * parValue) / marketPrice;
			} else {
				throw new stockException("Invalid StockType");
			}
		} else {
			throw new stockException("Invalid tickerPrice");
		}
		return dividendYield;
	}

	// Override function in case the price is passed.
	public double calcDividendYield(Double price) throws stockException {
		double dividendYield = 0.0;
		if (price > 0.0) {
			if (stockType == StockType.COMMON) {
				dividendYield = lastDividend / price;
			} else if (stockType == StockType.PREFERRED) {
				// PREFERRED
				dividendYield = (fixedDividend * parValue) / price;
			} else {
				throw new stockException("Invalid Stock Type");
			}
		} else {
			throw new stockException("Invalid Price");
		}
		return dividendYield;
	}

	public double calcPERatio() throws stockException {
		double peRatio = 0.0;

		if (marketPrice > 0.0) {
			peRatio = marketPrice / calcDividendYield(this.marketPrice);
		} else {
			throw new stockException("Invalid Market Price");
		}

		return peRatio;
	}
}