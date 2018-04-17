import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ActiveAttacker {

	public static void main(String[] args) {
		
		// Initialises 2 different Key Exchanges, for Alice & Eve, and Eve & Bob
		KeyExchange aliceEve = new KeyExchange();
		KeyExchange eveBob = new KeyExchange();
		
		// Initialises various BigInteger lists 
		List<BigInteger> aliceMsg1 = new ArrayList<BigInteger>();
		List<BigInteger> aliceMsg2 = new ArrayList<BigInteger>();
		List<BigInteger> bobMsg1 = new ArrayList<BigInteger>();
		List<BigInteger> bobMsg2 = new ArrayList<BigInteger>();
		
		// Eve intercepting Alice
		aliceMsg1 = aliceEve.ComputeMessageAtoB();
		aliceMsg2 = aliceEve.ComputeMessageBtoA(aliceMsg1);
		
		aliceEve.ComputeKeyA(aliceMsg2);
		aliceEve.ComputeKeyB(aliceMsg1);
		
		// Eve intercepting Bob
		bobMsg1 = eveBob.ComputeMessageAtoB();
		bobMsg2 = eveBob.ComputeMessageBtoA(bobMsg1);
		
		eveBob.ComputeKeyA(bobMsg2);
		eveBob.ComputeKeyB(bobMsg1);
		
		// Transcribes both key Exchanges to txt files
		try {
			aliceEve.printTranscript("aliceEve.txt");
			eveBob.printTranscript("eveBob.txt");
		} catch (IOException e) {
			System.out.println("Input/Output Exception thrown");
			e.printStackTrace();
		}
	}
}
