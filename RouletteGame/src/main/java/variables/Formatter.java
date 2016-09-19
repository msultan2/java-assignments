package variables;

import Roulette.Spinner.DivType;

public class Formatter {
	public String coloring(int spinResultValue,DivType spinResultType){
		//gets the corresponding background color of the generated number
		BackgroundColor backgroupColor=new BackgroundColor();
		DivType numType = null;
		if (spinResultValue==0){
			return backgroupColor.getGreen();
		} 
		if((spinResultValue >=1 && spinResultValue<=10) || 
				(spinResultValue >=19 && spinResultValue<=28))
				{
				if (spinResultType==numType.ODD) return backgroupColor.getRed();
					else return backgroupColor.getBlack();
				} else {
						if (spinResultType==numType.EVEN) return backgroupColor.getRed();
							else return backgroupColor.getBlack();
					}		
		}
	public DivType checkNumberType (int num){
		// returns the number type Odd/Even/Zero 		
		if (num==0){
			return DivType.Zero;
		}
		
		if (num % 2 == 0) 
			{return DivType.EVEN; }
		else{return DivType.ODD;
		}
	}

}
