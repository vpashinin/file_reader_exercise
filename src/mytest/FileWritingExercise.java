package mytest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileWritingExercise {

public static void main (String[] args) throws IOException {
	List<String> lines = new ArrayList<String>();
	
	File fout = new File("/home/vpashinin/out.txt");
	FileOutputStream fos = new FileOutputStream(fout);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	
	lines.add("line 1");
	lines.add("line 2");  
	lines.add("line 3");
	lines.add("line 4");
	lines.add("line 5");
	lines.add("line 6");
	lines.add("line 7");
	lines.add("line 8");


     for (String line : lines) { 
	
		bw.write(line);
		bw.newLine();

     }
     bw.close();

    }

 }



