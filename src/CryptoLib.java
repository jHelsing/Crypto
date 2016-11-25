public class CryptoLib {

	/**
	 * Returns an array "result" with the values "result[0] = gcd",
	 * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
	 * common divisor of "a" and "b", and "gcd = a * z + b * y".
	 **/
	public static int[] EEA(int a, int b) {
		// Note: as you can see in the test suite,
		// your function should work for any (positive) value of a and b.
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
	
	private static int recursiveGCD(int oldRest, int newRest){
		if(newRest == 0){
			return oldRest;
		}
		int temp = newRest;
		newRest =  oldRest % newRest;
		oldRest = temp;
		
		return recursiveGCD(oldRest, newRest);
	}
	
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
		return -1;
	}

	/**
	 * Returns the probability that calling a perfect hash function with
	 * "n_samples" (uniformly distributed) will give one collision (i.e. that
	 * two samples result in the same hash) -- where "size" is the number of
	 * different output values the hash function can produce.
	 **/
	public static double HashCP(double n_samples, double size) {
		return -1;
	}

}
