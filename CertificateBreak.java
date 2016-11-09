//credit to https://web.archive.org/web/20110516145331/http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner.html for file to string


import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CertificateBreak {
	
	public static void main(String args[]) {

		String data = "";
		String malicious = "";
		
		
		try {
			data = new Scanner(new File("hello.py")).useDelimiter("\\A").next();
			
			malicious = new Scanner(new File("malicious.py")).useDelimiter("\\A").next();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found. Check spelling and " +
				"location of file. Exiting.");
			return;
		}
		
		Random r = new Random();
		
		int cert = data.hashCode();
		int fakeCert = malicious.hashCode();
		
		String junkData = "";
		
		System.out.print("Generating file...");
		
		while(true) {
			for(int j = 0; j < r.nextInt(100) + 100; j++) {
				// generate random string, hash the string
				junkData += (char)(r.nextInt(74) + '0');
				fakeCert = (malicious + "#" + junkData).hashCode();
			}
			if(cert == fakeCert) {
				System.out.println(" done.");
				break;
			}
			junkData = "";
		}
		
		String finalFile = malicious + "#" + junkData;
		
		System.out.println("\n\n" + finalFile);
		
		
		System.out.println("\n\nOriginal hash: " + data.hashCode());
		System.out.println("Generated file hash: " + finalFile.hashCode());
	
	}
}
			
			