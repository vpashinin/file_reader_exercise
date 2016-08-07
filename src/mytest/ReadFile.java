package mytest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	private String path;
	  
	
	
	public ReadFile(String file_path){
		path = file_path;
	}
		
	public List<String> OpenFile() throws IOException{

		InputStreamReader is = new InputStreamReader(new FileInputStream(path), Charset.forName("latin1"));
		BufferedReader textReader = new BufferedReader(is);
	
		List<String> textData = new ArrayList<>();
		
		String line = textReader.readLine();
		while (line != null) {
			textData.add(line);
			line = textReader.readLine();	
		}
		
		textReader.close();
		return textData;
	}
	
	
	
	int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
	
		String aLine;
		int numberOfLines=0;
		while ((aLine=bf.readLine()) !=null) {
			numberOfLines++;
			
		}
		bf.close();
		return numberOfLines;
		
	}

}
