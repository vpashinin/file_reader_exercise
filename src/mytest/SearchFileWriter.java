package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFileWriter {

	/**
	 * @param args
	 */
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
//	replacements.put( %28n16%29%2c);
//	replacements.put( cc%2fcd);
//  replacements.put(  1%2c6);
//  replacements.put(  %9c%);
	
	
	
		
		    File destinationFile = new File("/home/vpashinin/searchqueries.csv");
		FileOutputStream fos = new FileOutputStream(destinationFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("search query;contains manufacturer;contains EAN;contains category;contains brend;contains model;");
		bw.newLine();
		
		
		File currentDir = new File("/home/vpashinin/accesslogs"); // current directory // programma  vivodit file na displey
		
		File[] files = currentDir.listFiles();
        
		List<String> manufacturers = readLines("/home/vpashinin/manufacturers.txt");
		System.out.println("Manufacturers:" +manufacturers);
		List<String> categories = readLines("/home/vpashinin/categorys.txt");         
		System.out.println("Categorys:" + categories); //?????????
		List<String> makes = readBrends();
		System.out.println("Brends:" +makes);
		List<String> models = readModels();
		System.out.println("Models:" +models);
		
		
		FileData fileData = new FileData(categories, makes, models, manufacturers);
		
		for (File file : files) {
 		     String fileName = file.getCanonicalPath();
 		     fileData.readLogfile(fileName, manufacturers, categories, models, makes, replacements, bw);

		 
         }
		bw.close(); 
		
	}
	
	
	private static List<String> readLines(String fileName) throws IOException {   
        InputStreamReader is = new InputStreamReader(new FileInputStream(fileName), Charset.forName("utf8"));
	    BufferedReader textReader = new BufferedReader(is);
        List<String> categorys = new ArrayList<>();              
	    String line = textReader.readLine();
	    while (line != null) {
		      categorys.add(line.toLowerCase());
		      line = textReader.readLine();		
        }
	    textReader.close();
	    return categorys;
     }
	
	
 }
	   


}
