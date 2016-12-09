
/**
 * We used the number 199412248594 to generate the values used in this assignment.
 * 
 * @author Jonathan and Amar
 * @version 1.0
 *
 */

public class CryptoLib {

	/**
	 * Returns an array "result" with the values "result[0] = gcd",
	 * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
	 * common divisor of "a" and "b", and "gcd = a * z + b * y".
	 **/
	public static int[] EEA(int a, int b) {
		int gcd = -1;
		int z = -1;
		int y = -1;
		int[] result = new int[3];
		
		boolean isSwapped = false;
		
		if(b>a) {
			int temp = a;
			a=b;
			b=temp;
			isSwapped = true;
		} else if(a==b) {
			result[0] = a;
			result[1] = 1;
			result[2] = 0;
			return result;
		}
		
		gcd = recursiveGCD(a, b);
		
		int[] arr = recursiveExtended(b, a);
		z = arr[0];
		y = arr[1];
		
		result[0] = gcd;
		if(isSwapped){
			int temp = z;
			z=y;
			y=temp;
		}
		result[1] = z;
		result[2] = y;
		return result;
	}
	
	/**
	 * Recursive function for calculating the greatest common divider between the two parameters.
	 * @param oldRest
	 * @param newRest
	 * @return The greatest common divider between oldRest and newRest.
	 */
	private static int recursiveGCD(int oldRest, int newRest){
		if(newRest == 0){
			return oldRest;
		}
		int temp = newRest;
		newRest =  oldRest % newRest;
		oldRest = temp;
		
		return recursiveGCD(oldRest, newRest);
	}
	
	/**
	 * Help method for calculate the extended part of the Extended Euclidean algorithm.
	 * @param a
	 * @param b
	 * @return
	 */
	private static int[] recursiveExtended(int a, int b) {
		if (a == 0) {
			int[] arr = {1,0};
			return arr;
		}
		int result[] = new int[2];
		int div = b / a;
		result = recursiveExtended(b % a, a);
		int temp = result[0] - result[1]*div;
		result[0] = result[1];
		result [1] = temp;
		return result;
	}

	/**
	 * Returns Euler's Totient for value "n".
	 **/
	public static int EulerPhi(int n) {
		int counter=0;
		for(int i=1; i < n;i++){
			if(recursiveGCD(n,i) == 1)
			counter++;
		}
		return counter;
	}

	/**
	 * Returns the value "v" such that "n*v = 1 (mod m)". Returns 0 if the
	 * modular inverse does not exist.
	 **/
	public static int ModInv(int n, int m) {
		int[] ans = new int[2];
		if(recursiveGCD(n,m)==1) {
			ans = recursiveExtended(m,n);
		} else if (n < 0) {
			while (n<=0) {
				n=n+m;
			}
			return ModInv(n,m);
		} else {
			return 0;
		}
		
		if(ans[0] < 0) {
			int i = ans[0];
			while (i<0) {
				i=i+m;
			}
			ans[0] = i;
		}
		return ans[0];
	}

	/**
	 * Returns 0 if "n" is a Fermat Prime, otherwise it returns the lowest
	 * Fermat Witness. Tests values from 2 (inclusive) to "n/3" (exclusive).
	 **/
	public static int FermatPT(int n) {
		
		for(int i=2; i < n/3; i++) {
			int num = modulus(i, 1, n-1, n);
			num = num % n;
			if(num != 1){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Calculates the modulus value of a^power in modulus mod. This is done recursively,
	 * so current represent the value that we have amassed at the moment.
	 * @param a - The base value of the calculation.
	 * @param current - The current value of the calculation.
	 * @param power - How many times a should be multiplied by itself.
	 * @param mod - The modulus used in this calculation.
	 * @return The value of a to the power of power in modulus mod.
	 */
	private static int modulus(final int a, int current, int power, final int mod) {
		
		while(power > 0) {
			current = current*a;
			while(current > mod) {
				current = current-mod;
			}
			power--;
		}
		
		return current;
	}

	/**
	 * Returns the probability that calling a perfect hash function with
	 * "n_samples" (uniformly distributed) will give one collision (i.e. that
	 * two samples result in the same hash) -- where "size" is the number of
	 * different output values the hash function can produce.
	 **/
	public static double HashCP(double n_samples, double size) {
		double top = 1;
		int count = 1;
		/* Simplify the calculation by removing the values that
		 * are above the division and below
		 */
		for(double i=size-n_samples+1; i<size; i++) {
			top *= i;
			/* Check if the value above the division is dividable with size
			 * if it is we will divide top with size. In order to not pass the
			 * max value for double.
			 */
			if(top%size == 0 && count < n_samples) {
				count++;
				top = top/size;
			}
		}
		
		/* Calculates the value below the division sign,
		 * makes sure that the number of times that we already have
		 * divided the top by is removed from size^n_samples
		 */
		double bottom = Math.pow(size, n_samples-count);
		
		return 1-(top/bottom);
	}

}
