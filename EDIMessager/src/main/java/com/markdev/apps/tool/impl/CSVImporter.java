package com.markdev.apps.tool.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.markdev.apps.tool.ColumnFilter;
import com.markdev.apps.tool.Importer;
import com.markdev.apps.tool.Parser;

public class CSVImporter implements Importer {


	private File analyzedFile;
	private Parser parser;
	
	
	
	public CSVImporter(File file,Parser parser){
		this.analyzedFile = file;
		this.parser = parser;
		
	}
	

	
	public void importData() {
		if (this.analyzedFile == null)
			throw new RuntimeException("Niepoprawna referencja do pliku");
		
		if (this.parser == null)
			throw new RuntimeException("Niepoprawna referencja do parsera");
		
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new FileReader(this.analyzedFile));
			 String line = null;
			 
			 long counter = 0;
			 
			 parser.parseHeader(reader.readLine());
			 
			 while ((line=reader.readLine())!=null){
				 
				 parser.parseLine(line,counter);
				 counter++;
			 }
			 
			 
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		finally{
			try {
				if (reader!=null)
				  reader.close();
			} catch (IOException e) {}
		}
	    
		
		
		
		
	}

    

}
