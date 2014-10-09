package com.markdev.apps.tool;

import java.io.File;

import com.markdev.apps.tool.impl.CSVImporter;
import com.markdev.apps.tool.impl.ConsoleExporter;
import com.markdev.apps.tool.impl.ParserImpl;
import com.markdev.apps.tool.impl.VelocityFormatter;

public class Main {

	private static final String PATH="EDI.txt";
	private static final String TEMPLATE = "output.vm";
	private static final String OUTFILE = "body.html";
	
	public static void main(String[] args) {
		
		
		File file = new File(PATH);
		String  tmplFileName = TEMPLATE;
		File outFile = new File(OUTFILE);
		
		
		if (args.length==3){
			file = new File(args[0]);
			tmplFileName = args[1];
			outFile = new File(args[2]);
		}

			
		
		
		
		
		Parser parser = new ParserImpl(new ColumnFilter() {
			
			//all kolumn
			public boolean apply(int i) {
				return true;
			}
		});
		
		//importuj dane z pliku do wewnętrznej reprezentacji
		Importer importer = new CSVImporter(file, parser);
		importer.importData();
		
		
		//przetworz dane do postaci zagregowanej
		Collector collector = new Collector(parser.data());
		collector.collect();
		
//		Exporter exp = new FileExporter(outFile);
		Exporter exp = new ConsoleExporter();
		
		//formatuj wynik i exportuj go np: na konsole
		Formatter formatter = new VelocityFormatter(collector,TEMPLATE);
		formatter.formatTo(exp);
		
		
	}
	
}
