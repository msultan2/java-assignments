package Roulette;

import variables.BackgroundColor;
import variables.Bet;
import variables.Formatter;
import variables.RouletteGameException;

/**
 * @author Msultan
 *
 */
public class Spinner {
	/*
	 * Generates random number between 0 to 36 to simulate the action resultant
	 * of spinning the roulette also it shows the color of the result based on
	 * the below formula In number ranges from 1 to 10 and 19 to 28, odd numbers
	 * are red and even are black. In ranges from 11 to 18 and 29 to 36, odd
	 * numbers are black and even are red. Even or Odd :result = num % 2 -- this
	 * result is 0 for even, 1 for odd numbers
	 */
	public Bet spinResult = new Bet();

	public enum DivType {
		EVEN, ODD, Zero
	}

	public enum BetType {
		Straight, EvenOrOdd, betStage4
	}

	public void Spinner() {

	}

	public int Spinner(Bet bet, BetType betType) throws RouletteGameException {
		// Generating init values
		init();
		int betResult = checkWinnings(bet, betType);
		bet.setWinningsAmount(bet.getWinningsAmount() + betResult);
		return betResult;
	}

	public int checkWinnings(Bet bet, BetType choiceBetType) throws RouletteGameException {
		// Raise exception in case of invalid bets
		if (bet.getBetValue() <= 0) {
			throw new RouletteGameException("Please Select a Bet Greater Than 0");
		}
		// Raise exception in case of invalid Pockets
		if ((Integer.valueOf(bet.getPocketValue()) <= 0 || Integer.valueOf(bet.getPocketValue()) > 36)
				&& bet.getPocketValue().compareTo("00") != 0 && bet.getPocketValue().compareTo("0") != 0) {
			throw new RouletteGameException("Invalid Pocket, Please Select a Pocket From 0 to 36");
		}

		// double the bet in case of successful guess of odd/even
		if (choiceBetType == BetType.EvenOrOdd) {
			if (Integer.valueOf(spinResult.getPocketValue()) == 0) {
				return 0;
			}
			if (spinResult.getDivType() == bet.getDivType()) {
				return bet.getBetValue() * 2;
			}
		}

		// 36 times the bet in case of successful guess of right pocket
		if (choiceBetType == BetType.Straight || choiceBetType == null) {
			if (spinResult.getPocketValue().compareTo(bet.getPocketValue()) == 0) {
				return bet.getBetValue() * 36;
			} else {
				return 0;
			}
		}
		return 0;
	}

	public void init() {
		// simulates the spinning effect
		Formatter formatter = new Formatter();
		int spinOutput = rand(0, 37);
		if (spinOutput == 37) {
			spinResult.setPocketValue("00");
		} else {
			spinResult.setPocketValue(String.valueOf(spinOutput));
		}

		spinResult.setDivType(formatter.checkNumberType(Integer.valueOf(spinResult.getPocketValue())));
		spinResult.setPocketColor(
				formatter.coloring(Integer.valueOf(spinResult.getPocketValue()), spinResult.getDivType()));
	}

	private int rand(int low, int high)
	// generates random number between low and high
	{
		return (int) (Math.floor(Math.random() * (high - low + 1) + low));
	}
}
