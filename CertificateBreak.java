//credit to https://web.archive.org/web/20110516145331/http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner.html for file to string


import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Given an original file and a 'malicious' file, CertificateBreak trivially 
 * modifies the 'malicious' file so that the java hashCode() of each file is
 * identical.
 */
public class CertificateBreak {
	
	public static void main(String args[]) {

		String data = "";
		String malicious = "";
		
		String dataFile = "hello.py";
		String malFile = "malicious.py";
		
		if(args.length == 0) {
			System.out.println("No parameters specified, using default files");
		} else if(args.length == 2) {
			dataFile = args[0];
			malFile = args[1];
		} else {
		  System.out.println("Incorrect usage, exiting program. Correct usage:");
			System.out.println("\tjava CertificateBreak (original file) " +
			    "('malicious' file)");
			System.out.println("If no parameters are specified, the default files " +
			    "are used. (hello.py and malicious.py)");
			return;
		}
		
		try {
			data = new Scanner(new File(dataFile)).useDelimiter("\\A").next();
			
			malicious = new Scanner(new File(malFile)).useDelimiter("\\A").next();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found. Check spelling and location of " +
				"file. Exiting.");
			return;
		}
		
		String comment = getCommentCode(malFile);
		
		Random r = new Random();
		
		int cert = data.hashCode();
		int fakeCert = malicious.hashCode();
		
		String junkData = "";
		
		System.out.print("Generating file...");
		
		while(true) {
		  //generate string with 150 +/- 50 characters
			for(int j = 0; j < r.nextInt(100) + 100; j++) {
				// generate random string, hash the string
				junkData += (char)(r.nextInt(74) + '0');
				fakeCert = (malicious + comment + junkData).hashCode();
			}
			if(cert == fakeCert) {
				System.out.println(" done.");
				break;
			}
			junkData = "";
		}
		
		String finalFile = malicious + comment + junkData;
		
		System.out.println("\n\n" + finalFile);
		
		
		System.out.println("\n\nOriginal hash: " + data.hashCode());
		System.out.println("Generated file hash: " + finalFile.hashCode());
	
	}
	
	/**
	 * From a filename's extension, determine he string needed to initiate a 
	 * comment.
	 */
	public static String getCommentCode(String filename) {
	  String ext = filename.substring(filename.lastIndexOf('.'),
	      filename.length());
	  switch(ext) {
	  case ".py":
	  case ".rb":
	    return "#";
	  case ".c":
	  case ".h":
	  case ".java":
	  case ".cpp":
	  case ".hpp":
	  case ".cxx":
	  case ".js":
	  case ".cs":
	  case ".go":
	    return "//";
	  }
	  return "";
	}
}
			
			