import java.util.Random;

public class HashBreak {
	
	public static void main(String args[]) {
		//Java hashcode() outputs a 32bit integer.
		//By the birthday paradox, there is a 50% chance of finding a collision
		//in 1.2*2^(n/2) hashes.
		//2^16 = 65,536
		
		String[] msg = new String[65536];
		int[] hashes = new int[65536];
		
		Random r = new Random();
		int loopCounter = 1;
		
		System.out.print("Searching for java hashcode collision");
		
		while(true) { //loop until a collision is found
			
			System.out.print("."); 
			
			for(int i = 0; i < 65536; i++) {
				
				msg[i] = "";
				
				for(int j = 0; j < r.nextInt(20) + 10; j++) {
					// generate random string, hash the string
					msg[i] += (char)(r.nextInt(74) + '0');
					hashes[i] = msg[i].hashCode();
					
				}
			}
			
			for(int i = 0; i < 65536; i++) {
				for(int j = i + 1; j < 65536; j++) {
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