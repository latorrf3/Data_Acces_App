package com.flc.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * It deals with writing methods of the program
 * @author Federico Latorre
 *
 */
public class Writer {
	
	/**
	 * Method that prints a text into a file
	 * @param file object of the class File
	 * @param text String that contains the text that has to be printed
	 */
	public void writerInData(File file, String text) {
		try (FileWriter dataWriter = new FileWriter(file);
				PrintWriter output = new PrintWriter(dataWriter);) {
			
			output.println(" ------- ");
			output.println(text);
			output.println(" ------- ");
			
		} catch (IOException writerException) {
			writerException.printStackTrace();
		}
	}

}
