package Roulette;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Roulette.Spinner;
import Roulette.Spinner.BetType;
import Roulette.Spinner.DivType;
import variables.Bet;
import variables.Formatter;
import variables.RouletteGameException;

public class SpinnerTest {
	Spinner spinner = new Spinner();
	Bet userBet = new Bet();

	@Before
	public void before() {
		userBet.setBetValue(10);
	}

	@After
	public void after() {
		userBet.setBetValue(0);
		userBet.setPocketValue("-1");
	}

	@Test
	// Checking Even and odd funclionality
	public void evenOddFunctionality() {
		Formatter formatter = new Formatter();
		assertEquals(formatter.checkNumberType(5), DivType.ODD);
		assertEquals(formatter.checkNumberType(20), DivType.EVEN);
		assertEquals(formatter.checkNumberType(0), DivType.Zero);
	}

	// Checking Spinnier init functionality
	public void initFunctionality() {
		int high = 37;
		int low = 0;
		for (int i = 0; i <= 50; i++) {
			spinner.init();
			assertTrue("Rand generates higher number", high >= Integer.valueOf(spinner.spinResult.getPocketValue()));
			assertTrue("Rand generates lower number", low <= Integer.valueOf(spinner.spinResult.getPocketValue()));
		}

	}

	@Test
	public void loosingPocket() {
		// Stage1: I spin the roulette wheel and ball lands in a losing pocket
		testSpinner("4", DivType.EVEN);
		userBet.setPocketValue("7");
		int expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);
	}

	@Test
	public void winningPocket() {
		// Stage1: I spin the roulette wheel and ball lands in a winning pocket
		userBet.setPocketValue("4");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 360);
		
		userBet.setPocketValue("13");
		testSpinner("13", DivType.ODD);
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 360);

		userBet.setPocketValue("00");
		testSpinner("0", DivType.Zero);
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);

		userBet.setPocketValue("00");
		testSpinner("0", DivType.Zero);
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);

		userBet.setPocketValue("00");
		testSpinner("00", DivType.Zero);
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 360);

		userBet.setPocketValue("0");
		testSpinner("0", DivType.Zero);
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 360);
	}

	@Test(expected = RouletteGameException.class)
	public void badBet() throws RouletteGameException {
		userBet.setBetValue(-1);
		testSpinner("4", DivType.EVEN);
		getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test(expected = RouletteGameException.class)
	public void badPocket000() throws RouletteGameException {
		userBet.setPocketValue("000");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test(expected = RouletteGameException.class)
	public void badPocket39() throws RouletteGameException {
		userBet.setPocketValue("39");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test(expected = RouletteGameException.class)
	public void badPocket() throws RouletteGameException {
		userBet.setPocketValue("-1");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test
	public void normalPocket15() throws RouletteGameException {
		userBet.setPocketValue("15");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test
	public void badPocket0() throws RouletteGameException {
		userBet.setPocketValue("0");
		testSpinner("4", DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
	}

	@Test
	public void zeroPocket() {
		// Stage3 (odd or Even:I spin the roulette wheel and the ball lands in
		// pocket 0)
		testSpinner("0", DivType.Zero);
		userBet.setPocketValue("4");
		int expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);

		testSpinner("0", DivType.Zero);
		userBet.setPocketValue("3");
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);
		
		testSpinner("00", DivType.Zero);
		userBet.setPocketValue("3");
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);
		
		testSpinner("00", DivType.Zero);
		userBet.setPocketValue("4");
		expectedBetResult = getBetResult(userBet);
		assertEquals(expectedBetResult, 0);
	}

	@Test
	public void evenIN_EvenOut() throws RouletteGameException {
		// Given A customer has placed a bet of £10 on even
		// When I spin the roulette wheel and the ball lands on an even pocket
		testSpinner("6", DivType.EVEN);
		userBet.setPocketValue("30");
		userBet.setDivType(DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
		assertEquals(expectedBetResult, 20);
	}

	@Test
	public void evenIN_OddOut() throws RouletteGameException {
		// Given A customer has placed a bet of £10 on even
		// When I spin the roulette wheel and the ball lands on an even pocket
		testSpinner("31", DivType.ODD);
		userBet.setPocketValue("30");
		userBet.setDivType(DivType.EVEN);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
		assertEquals(expectedBetResult, 0);
	}

	@Test
	public void oddIN_EvenOut() throws RouletteGameException {
		// Given A customer has placed a bet of £10 on even
		// When I spin the roulette wheel and the ball lands on an even pocket
		testSpinner("22", DivType.EVEN);
		userBet.setPocketValue("31");
		userBet.setDivType(DivType.ODD);
		int expectedBetResult = getBetResult(userBet, BetType.EvenOrOdd);
		assertEquals(expectedBetResult, 0);
	}

	@Test
	public void oddIN_OddOut() throws RouletteGameException {
		// Given A customer has placed a bet of £10 on odd
		// When I spin the roulette wheel and the ball lands on an odd pocket
		userBet.setPocketValue("19");
		userBet.setDivType(DivType.ODD);
		testSpinner("17", DivType.ODD);
		assertEquals(getBetResult(userBet, BetType.EvenOrOdd), 20);
	}

	private int getBetResult(Bet bet, BetType betType) throws RouletteGameException {
		return spinner.checkWinnings(userBet, betType);
	}

	private int getBetResult(Bet bet) {
		try {
			return spinner.checkWinnings(userBet, null);
		} catch (RouletteGameException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Error:" + e.getMessage());
		}
		return 0;
	}

	public void testSpinner(String PocketValue, DivType divType) {
		spinner.spinResult.setPocketValue(PocketValue);
		spinner.spinResult.setDivType(divType);
	}

}
