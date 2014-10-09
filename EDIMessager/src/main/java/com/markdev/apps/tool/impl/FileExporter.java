package com.markdev.apps.tool.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.markdev.apps.tool.Exporter;

public class FileExporter implements Exporter {

	private File file;
	
	public FileExporter(File tmplFile) {
		this.file=tmplFile;
	}

	public void export(Writer wr) {
		if (file!=null){
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(wr.toString());
								
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
