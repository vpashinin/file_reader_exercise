package mytest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class FileData {
	
	
	 
	private static void printSearchText(String line, Map<String, String> exampleMap, BufferedWriter bw, List<String> manufacturers) throws IOException{
		int position = line.indexOf("text");
		


        String piece = line.substring(position); 
         int positionOfBlank = piece.indexOf(" ");
        String searchText = piece.substring(0, positionOfBlank);

        for (Entry<String, String> mapping : exampleMap.entrySet()) {
       	 	String key = mapping.getKey();
       	 	String value = mapping.getValue();
       	 	searchText = searchText.replaceAll(key, value);    
        }
	      					      
		String searchQuery = searchText.substring(5);
		
		// zdes videlyaem nuzniy  text do nuznogo  znaca  v nashem primere etot znak  "&" i programma vidast text v ctroke do etogo znaka
		 int positionOfAnd = searchQuery.indexOf("&"); 
		 if(positionOfAnd>-1){
                   searchQuery = searchQuery.substring(0, positionOfAnd);
		 }
		 // comanda chtobi ne bilo probelov v text kotoriy v terminale
		    searchQuery = searchQuery.trim();
	   
		 if (!searchQuery.equals("")) {				 
			 String resultLine = computeResultLine(searchQuery, manufacturers);

			 bw.write(resultLine);
			 bw.newLine();
		 } 
	     
	}

	private static String computeResultLine(String searchQuery, List<String> manufacturers) {
		 String resultLine = searchQuery;
		 resultLine = resultLine +";";
		 
		 /*
		  * Manufacturer ?
		  */
		 if (containsManufacturer(searchQuery, manufacturers)){
		         resultLine = resultLine + "1";
	
		 } else { 
		         resultLine = resultLine + "0";
		 }
		 resultLine = resultLine +";";
		 
		 /*
		  * EAN ?
		  */
		 if (containsEAN(searchQuery)){
		         resultLine = resultLine + "1";
		 } else { 
		         resultLine = resultLine + "0";
		 }
		 
		    return resultLine;
	}
		 
		 
	private static boolean containsEAN(String searchQuery) {
		
		/*
		 * Split into words
		 */
		String[] words = searchQuery.split(" ");
		for(String word:words){
			int length = word.length();
			
			if(13==length && containsOnlyDigits(word)) {
				return  true;
			   
			}
			
		}
		
		return false;
		/*
		 * Is a word contains 13 characters?
		 * .length()
		 * 
		 */
		
	}

	private static boolean containsOnlyDigits(String word) {
		char[] characters = word.toCharArray();
		for (char character : characters) {
			  if(!Character.isDigit(character)){
			  
				  return false;
				  
				  
			  }
 
		       		       
		}
		return true;
	}
	               
		                 
//		         return false;
		 
	
	

	private static boolean containsManufacturer(String searchQuery, List<String> manufacturers) {		
		
		String[] words = searchQuery.split(" ");
		
		for (String manufacturer : manufacturers) {
			for (String word : words) {
				if( manufacturer.equals(word)){
					return true;
				}
			}
		
		}  
		                             
		return false;
   }
	
	


	
	private static void readLogfile(String fileName, List<String> manufacturers, Map<String, String> replacements, BufferedWriter bw) throws IOException {
		ReadFile file = new ReadFile (fileName);
		List<String> lines = file.OpenFile();
	
		 String searchWord1 = "GET /szukaj";
		 String searchWord2 = "text"; 
		 String searchWord3 = "bingbot";
		 String searchWord4 = "googlebot";
		     
		 for (String line : lines){ 
			 if (line.contains(searchWord1) && line.contains(searchWord2) && !line.contains(searchWord3) && !line.contains(searchWord4)) {
					 printSearchText(line.toLowerCase(), replacements, bw, manufacturers); 
				
		     }
			 
    	 }
	}
	public static void main(String[] args)throws IOException {
				
		Map<String, String> replacements = new HashMap<>();
		
		replacements.put("%20", " ");
  		replacements.put("%C5%82", "ł");
  		replacements.put("%C4%85", "ą");
  		replacements.put("%C3%83", "Ã");
  		replacements.put("%C5%85", "Ņ");
  		replacements.put("%C2%85", " ");
  		replacements.put("%C2%82", " ");
  		replacements.put("%C3%82", "Â ");
  		replacements.put("%C3%84", "Ä");
  		replacements.put("%C2%83", " ");
  		replacements.put("%C4%89", "ĉ");
  		replacements.put("%C3%B3", "ó");
  		replacements.put("%C4%99", "ę");
  		replacements.put("%C2%BC", "¼");	
  		replacements.put("%C2%B3", "³");	
  		replacements.put("%C3%B3", "ó");	
  		replacements.put("%C5%9A", "Ś");	
  		replacements.put("%C5%BC", "ż");
  		replacements.put("%C5%81", "Ł");		
  		replacements.put("%C4%98", "Ę");
		replacements.put("%C2%9B", " ");
		replacements.put("%C5%BA", "ź");
		replacements.put("%C5%84", "ń");
 		replacements.put("%C5%9B", "ś");
  		replacements.put("%C5%BB", "Ż");
  		replacements.put("%C2%84", " ");
  		replacements.put("%C2%99", " ");
		replacements.put("%C4%84", "Ą");
  		replacements.put("\\\\xC3\\\\x84", "Ä");
  		replacements.put("\\\\xC2\\\\x85", " ");
  		replacements.put("\\\\xC4\\\\x85", "ą");
  		replacements.put("\\\\xC3\\\\x84", "Ä");
  		replacements.put("\\\\xC3\\\\x85", "Å");
  		replacements.put("\\+", " ");
  		replacements.put("\\\\xC3\\\\x83", "Ã");
  		replacements.put("\\\\xE2\\\\x80", " ");
  		replacements.put("\\\\xE2\\\\x84", " ");
  		replacements.put("\\\\xC3\\\\xB3", "ó");
  		replacements.put("\\\\xC2\\\\xB3", "³");
  		replacements.put("\\\\xC2\\\\x99", " ");
  		replacements.put("\\\\xC2\\\\xBC", "¼");
  		replacements.put("%c4%85", "ą");
		replacements.put("%c5%82", "ł");
 		replacements.put("%c3%b3", "ó");
		replacements.put("%c4%99", "ę");
		replacements.put("%c5%9b", "ś");
		replacements.put("%c5%84", "ń");
		replacements.put("%c5%bc", "ż");
//		replacements.put( %28n16%29%2c);
//  	replacements.put( cc%2fcd);
//      replacements.put(  1%2c6);
		
		
		
  		
  		    File destinationFile = new File("/home/vpashinin/searchqueries.csv");
			FileOutputStream fos = new FileOutputStream(destinationFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			bw.write("search query;contains manufacturer;contains EAN");
			bw.newLine();
			
			// programma  vivodit file na displey
			File currentDir = new File("/home/vpashinin/accesslogs"); // current directory
			
			File[] files = currentDir.listFiles();
	        
			List<String> manufacturers = readManufacturers();
			System.out.println("Manufacturers:" +manufacturers);
			
			for (File file : files) {
	 		     String fileName = file.getCanonicalPath();
	 		     readLogfile(fileName, manufacturers, replacements, bw);

			 
	         }
			bw.close(); 
			
		}

	private static List<String> readManufacturers() throws IOException {  
		String fileName = "/home/vpashinin/manufacturers.txt"; 
	
		InputStreamReader is = new InputStreamReader(new FileInputStream(fileName), Charset.forName("utf8"));
		
		BufferedReader textReader = new BufferedReader(is);
	
		List<String> manufacturers = new ArrayList<>();                             // /home/vapashinin/manufacturers.txt
		String line = textReader.readLine();
		while (line != null) {
			manufacturers.add(line.toLowerCase());
			line = textReader.readLine();		
	    }
		textReader.close();
		return manufacturers;
	}
}

   








