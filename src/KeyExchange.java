import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyExchange {

	private List<BigInteger> msg1 = new ArrayList<BigInteger>();
	private List<BigInteger> msg2 = new ArrayList<BigInteger>();
	private BigInteger expA;
	private BigInteger expB;
	BigInteger keyA;
	BigInteger keyB;
	
	public static void main(String[] args){
		
		KeyExchange ke = new KeyExchange();
		
		ke.ComputeMessageAtoB();
		ke.ComputeMessageBtoA(ke.msg1);
		
		ke.ComputeKeyA(ke.msg2);
		ke.ComputeKeyB(ke.msg1);
		
		
		try {
			ke.printTranscript("output.txt");
		} catch (IOException e) {
			System.out.println("Input/Output Exception thrown");
			e.printStackTrace();
		}
	}
	
	// Prints the Transcript
	public void printTranscript(String fileName) throws IOException{
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		out.println("secretA = " + expA);
		out.println("secretB = " + expB);
		out.println("msg1.modulus = " + msg1.get(0));
		out.println("msg1.base = " + msg1.get(1));
		out.println("msg1.valueA = " + msg1.get(2));
		out.println("msg2.valueB = " + msg2.get(0));
		out.println("keyA = " + keyA);
		out.println("keyB = " + keyB);
		
		out.close();
	}
	
	public List<BigInteger> ComputeMessageAtoB(){
		Random rand = new Random();
		
		// Initialises BigIntegers
		BigInteger prime = new BigInteger(1024, 128, rand);
		BigInteger exponent = new BigInteger(1024, rand).add(BigInteger.valueOf(2));
		BigInteger base = new BigInteger(1024, rand).add(BigInteger.ONE);
		
		// Finds an exponent between 2 and prime - 1
		while(exponent.compareTo(prime) != -1){
			exponent = new BigInteger(1024, rand).add(BigInteger.valueOf(2));
		}
		// Finds a base between 1 and prime - 1
		while(base.compareTo(prime) != -1){
			base = new BigInteger(1024, rand).add(BigInteger.ONE);
		}
		expA = exponent;
		
		// Calculates ValueA
		BigInteger valueA = base.modPow(exponent, prime);
		
		msg1.add(prime);
		msg1.add(base);
		msg1.add(valueA);
		return msg1;
	}
	
	public List<BigInteger> ComputeMessageBtoA(List<BigInteger> msg1){
		BigInteger prime = msg1.get(0);
		BigInteger base = msg1.get(1);

		Random rand = new Random();
		BigInteger exponent = new BigInteger(1024, rand).add(BigInteger.valueOf(2));
		
		// Finds exponent between 2 and prime - 1
		while(exponent.compareTo(prime) != -1){
			exponent = new BigInteger(1024, rand).add(BigInteger.valueOf(2));
		}
		expB = exponent;
		
		// Calculates valueB
		BigInteger valueB = base.modPow(exponent, prime);
		msg2.add(valueB);
		return msg2;
	}
	
	public BigInteger ComputeKeyA(List<BigInteger> msg2){
		BigInteger keyA = msg2.get(0).modPow(expA, msg1.get(0));
		this.keyA = keyA;
		return keyA;
	}
	
	public BigInteger ComputeKeyB(List<BigInteger> msg1){
		BigInteger keyB = msg1.get(2).modPow(expB, msg1.get(0));
		this.keyB = keyB;
		return keyB;
	}
}
