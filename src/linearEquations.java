import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class linearEquations {

	public static void main(String[] args) {
		
		linearEquations l = new linearEquations();
		
		// Values from Question 2a
		BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
		BigInteger b = new BigInteger("45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");
		BigInteger N = new BigInteger("643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
		
		// Values from Question 2b
		/*BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
		BigInteger b = new BigInteger("24243252873562935279236582385723952735639239823957923562835832582635283562852252525256882909285959238420940257295265329820035324646");
		BigInteger N = new BigInteger("342487235325934582350235837853456029394235268328294285895982432387582570234238487625923289526382379523573265963293293839298595072093573204293092705623485273893582930285732889238492377364284728834632342522323422");
		*/
		
		// Solves linear equation of x
		BigInteger x = l.solver(a, b, N);
		
		// Prints out Computation results to file
		try {
			PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
			out.println("N = " + N);
			out.println("a = " + a);
			out.println("b = " + b);
			out.println("x = " + x);
			out.close();
		} catch (IOException e) {
			System.out.println("Input/Output Exception thrown");
			e.printStackTrace();
		}
	}

	public BigInteger solver(BigInteger a, BigInteger b, BigInteger N){
		
		// Calculates gcd of N & a
		BigInteger gcd = N.gcd(a);
		
		// Returns null if gcd of a & N isn't 1 (not coprime)
		if(!gcd.equals(BigInteger.ONE)){
			return null;
		}
		
		// Calculates multiplicative inverse of a mod N
		BigInteger inverse = a.modInverse(N);
		
		// Makes b negative
		b = b.negate();
		
		BigInteger inverse2 = inverse;
		
		BigInteger y = inverse2.multiply(b).mod(N);
		
		// Multiplies the inverse by -b mod N
		BigInteger x = inverse.multiply(b.mod(N));

		System.out.println("inverse x is: " + x);
		System.out.println("inverse y is: " + y);
		
		return x;
	}
	
	
}
