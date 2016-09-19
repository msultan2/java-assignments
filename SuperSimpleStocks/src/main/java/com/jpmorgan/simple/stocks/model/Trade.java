package com.jpmorgan.simple.stocks.model;

/**Used as obejct in Stock manager calucations
 * 
 * @author msultan
 *
 */
public class Trade {

	private Stock stock;
	private TradeType tradeIndicator;
	private int sharesQuantity;
	private double tradePrice;

	public Trade() {
		// Constructor
		// no initialization needed
	}

	public Trade(Stock stock, TradeType tradeIndicator, int sharesQuantity, double tradePrice) {
		this.stock = stock;
		this.tradeIndicator = tradeIndicator;
		this.sharesQuantity = sharesQuantity;
		this.tradePrice = tradePrice;
	}

	public int getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public TradeType getTradeIndicator() {
		return tradeIndicator;
	}

	public void setTradeIndicator(TradeType tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double price) {
		this.tradePrice = price;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}