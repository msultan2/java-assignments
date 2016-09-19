package variables;

import Roulette.Spinner.DivType;

public class Bet {
	String pocketColor="";
	DivType divType;
	String pocketValue="";
	Integer betValue;
	int winningsAmount=0;
	public String getPocketColor() {
		return pocketColor;
	}
	public DivType getDivType() {
		return divType;
	}
	public void setDivType(DivType divType) {
		this.divType = divType;
	}
	public int getWinningsAmount() {
		return winningsAmount;
	}
	public void setWinningsAmount(int winningsAmount) {
		this.winningsAmount = winningsAmount;
	}
	public Integer getBetValue() {
		return betValue;
	}
	public void setBetValue(Integer betValue) {
		this.betValue = betValue;
	}
	public void setPocketColor(String pocketColor) {
		this.pocketColor = pocketColor;
	}
	public String getPocketValue() {
		return pocketValue;
	}
	public void setPocketValue(String value) {
		this.pocketValue = value;
	}
	
}
