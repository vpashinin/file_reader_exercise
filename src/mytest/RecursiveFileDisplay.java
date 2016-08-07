package mytest;

// programma  vivodit file na displey

import java.io.File;
import java.io.IOException;

public class RecursiveFileDisplay {

	public static void main(String[] args) throws IOException {
		File currentDir = new File("/home/vpashinin/accesslogs"); // current directory
		displayDirectoryContents(currentDir);
	}

	public static void displayDirectoryContents(File dir) throws IOException{

		File[] files = dir.listFiles();
		for (File file : files) {
			System.out.println("     file:" + file.getCanonicalPath());
		}

	}

}





