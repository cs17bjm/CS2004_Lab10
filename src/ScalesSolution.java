import java.util.*;

public class ScalesSolution
{
	private Double D = null, V = null;
	//Creates a new scales solution based on a string parameter
	//The string parameter is checked to see if it contains all zeros and ones
	//Otherwise the random binary string generator is used (n = length of parameter)
	public ScalesSolution(Double degrees, Double velocity)
	{
		if (degrees >= 25 && degrees <= 55) {
			D = degrees;
		}
		else D = (double) Math.round(CS2004.UR(25.0, 55.0));
		if (velocity >= 1500 && velocity <= 1650) {
			V = velocity;
		}
		else V = (double) Math.round(CS2004.UR(1500.0, 1650.0));
	}


	//Display the string without a new line
	public void print()
	{
		System.out.print("Elevation = " + Double.toString(D) + " degrees \t Velocity = " + Double.toString(V) + "m/s");
	}
	//Display the string with a new line
	public void println()
	{
		System.out.println("Elevation = " + Double.toString(D) + " degrees \t Velocity = " + Double.toString(V)+ "m/s");
	}
	public void SmallChange()	//swaps a random element of the scasol
	{
		int decision = CS2004.UI(0, 1);
		if (decision == 0) {
			int change = CS2004.UI(-1, 1);
			D += change;
			if (D > 55.0) {D = 55.0;}
			if (D < 25.0) {D = 25.0;}
		}
		else {
			int change = CS2004.UI(-5, 5);
			V += change;
			if (V > 1650.0) {V = 1650.0;}
			if (V < 1500.0) {V = 1500.0;}
		}
	}
	public Double GetD() {
		return D;
	}
	public Double GetV() {
		return V;
	}
}