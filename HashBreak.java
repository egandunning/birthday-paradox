import java.util.Random;

/**
 * Finds a java hashCode collision using brute force. The output of hashCode is 
 * a 32-bit integer.
 */
public class HashBreak {
	
	public static void main(String args[]) {
		//Java hashcode() outputs a 32bit integer.
		//By the birthday paradox, there is a 50% chance of finding a hash function 
		//collision in 1.2*2^(n/2) hashes where n is the number of bits in the hash
		//function's output.
		//1.2 * 2^16 = 1.2 * 65,536 = 78643.2
		
		String[] msg = new String[78643];
		int[] hashes = new int[78643];
		
		Random r = new Random();
		int loopCounter = 1;
		
		System.out.print("Searching for java hashcode collision");
		
		while(true) { //loop until a collision is found
			
			System.out.print("."); 
			
			for(int i = 0; i < 78643; i++) {
				
				msg[i] = "";
				
				//random strings have 20 +/- 10 characters
				for(int j = 0; j < r.nextInt(20) + 10; j++) {
					// generate random string, hash the string
					msg[i] += (char)(r.nextInt(74) + '0');
					hashes[i] = msg[i].hashCode();
					
				}
			}
			
			//check if any of the random strings hash to the same value
			//by the birthday paradox, this should find a collision with .5 probability
			for(int i = 0; i < 78643; i++) {
				for(int j = i + 1; j < 78643; j++) {
					if(hashes[i] == hashes[j]) {
						System.out.println("\n" + loopCounter + " rounds to find a collision.");
						System.out.println("Hash: " + hashes[i] + " PT: " + msg[i]);
						System.out.println("Hash: " + hashes[j] + " PT: " + msg[j]);
						return; //Found a collision, exit program.
					}
				}
			}
			
			loopCounter++;
		}
		
	}
}