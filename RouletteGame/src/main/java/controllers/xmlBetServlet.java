package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Roulette.Spinner;
import Roulette.Spinner.BetType;
import Roulette.Spinner.DivType;
import variables.Bet;
import variables.RouletteGameException;

/**
 * Servlet implementation class Bet
 */
public class xmlBetServlet extends HttpServlet { 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		PrintWriter out=response.getWriter();
		Spinner spinner=new Spinner();
		
		//Receiving Parameters from HTML
		String betAmount=request.getParameter("betAmount");
		String betTypeS=request.getParameter("betType");
		String PocketValue=request.getParameter("PocketValue");
		Bet bet=new Bet();
		bet.setBetValue(Integer.valueOf(betAmount));
		int betResult = -1;
		
		if (betTypeS.compareTo("Straight")==0){
				bet.setPocketValue(PocketValue);
				try {
					betResult = spinner.Spinner(bet, BetType.Straight);
				} catch (RouletteGameException e) {
					// TODO Auto-generated catch block
					out.println("Excption Error:"+e.getMessage());
				}
				}
			else {
				if(PocketValue.compareTo("Even")==0)
				{bet.setDivType(DivType.EVEN);}
					else{bet.setDivType(DivType.ODD);}
				try {
					betResult = spinner.Spinner(bet, BetType.EvenOrOdd);
				} catch (RouletteGameException e) {
					// TODO Auto-generated catch block
					out.println("Excption Error:"+e.getMessage());
				}
				}
		//Getting System values
		Bet sysBet=spinner.spinResult;
		out.println("<b>Bet Input:</b>"+betAmount+"$");
		out.println("<b>Bet Result:</b>"+betResult+"$");
		out.println("<br>");
		out.println("<br>");
		out.println("Roulette Output");
		out.println("<br>");
		out.println("<b>Bet Pocket:</b>"+sysBet.getPocketValue());
		if(sysBet.getDivType()==DivType.EVEN)
			out.println("<b>Bet Type:</b> Even");
			else out.println("<b>Bet Type:</b> Odd");		
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
