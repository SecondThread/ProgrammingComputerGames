package math;

public class GeneralMath {
	
	/**
	 * Linear interpolation
	 * @param a
	 * The first double 
	 * @param b
	 * The second double
	 * @param percentToB
	 * The percent from a to b, where 0 is all the way to a, and 1 is all the way to b.
	 * @return
	 * The linear interpolation from A to B
	 */
	public static double lerp(double a, double b, double percentToB) {
		return b*percentToB+(1-percentToB)*a;
	}
}
