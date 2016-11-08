import java.util.Random;

public class BirthdayParadoxTest {
	
	public static void main(String args[]) {
		
		//Test to see if the birthday paradox holds for Java hashcode() by 
		//counting the number of rounds to find a collision.
		//If we compare the hashes of 1.2*2^16 strings per round, the average
		//number of rounds should be 2.
		
		if(args.length != 1) {
			System.out.println("Usage: java BirthdayParadoxTest [iterations]");
			System.out.println("Exiting.");
			return;
		}
		
		try {
			int iterations = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("\nUsage: java BirthdayParadoxTest [iterations]");
			System.out.println("iterations must be an integer. Exiting.");
			return;
		}
		
		int iterations = Integer.parseInt(args[0]);	
		
		int totalRounds = 0;
		
		for(int i = 0; i < iterations; i++) {
			
			totalRounds += roundsToFindCollision();	
			System.out.print(".");
		}
		System.out.println("\nAverage number of rounds: " + (totalRounds / (float)iterations));
	}
	
	public static int roundsToFindCollision() {
		
		//2^16 * 1.2 = 78643.2
		String[] msg = new String[78644];
		int[] hashes = new int[78644];
		
		Random r = new Random();
		int loopCounter = 1;
				
		while(true) { //loop until a collision is found
						
			for(int i = 0; i < 78644; i++) {
				
				msg[i] = "";
				
				for(int j = 0; j < r.nextInt(20) + 10; j++) {
					// generate random string, hash the string
					msg[i] += (char)(r.nextInt(74) + '0');
					hashes[i] = msg[i].hashCode();
					
				}
			}
			
			for(int i = 0; i < 78644; i++) {
				for(int j = i + 1; j < 78644; j++) {
					if(hashes[i] == hashes[j]) {
						return loopCounter; //Found a collision, exit method.
					}
				}
			}
			
			loopCounter++;
		}
		
	}
}
	