package com.markdev.apps.tool;

import java.io.File;

import com.markdev.apps.tool.impl.CSVImporter;
import com.markdev.apps.tool.impl.ConsoleExporter;
import com.markdev.apps.tool.impl.DescRepository;
import com.markdev.apps.tool.impl.FileDescRepository;
import com.markdev.apps.tool.impl.FileExporter;
import com.markdev.apps.tool.impl.ParserImpl;
import com.markdev.apps.tool.impl.VelocityFormatter;

public class Main {

	private static final String PATH="EDI.txt";
	private static final String REPOFILEPATH="opis.txt";
	private static final String TEMPLATE = "template.vm";
	private static final String OUTFILE = "body.html";
	private static final String OMITTED = "53";
	
	
	public static void main(String[] args) {
		
		
		File file = new File(PATH);
		String  tmplFileName = TEMPLATE;
		File outFile = new File(OUTFILE);
		File repoFile = new File(REPOFILEPATH);
		
		
		
		
		if (args.length==4){
			
			//plik wejściowy
			file = new File(args[0]);
			
			//scieżka pliku template
			tmplFileName = args[1];
			
			//plik wejściowy
			outFile = new File(args[2]);
			
			//plik z opisami komunikatow
			repoFile = new File(args[3]);
			
		}

			
		
		
		
		
		Parser parser = new ParserImpl(new Filter() {
			
			//all kolumn
			public boolean apply(int i) {
				return true;
			}

			public boolean apply(String[] data) {
				throw new RuntimeException("No used in this object");
			}
		});
		
		DescRepository repo=new FileDescRepository(repoFile);
		
		
		//importuj dane z pliku do wewnętrznej reprezentacji
		Importer importer = new CSVImporter(file, parser);
		importer.importData();
		
		
		//przetworz dane do postaci zagregowanej
		Collector collector = new Collector(parser.data(),new Filter() {
			
			
			public boolean apply(int i) {
				throw new RuntimeException("No used in this object");

			}

			//pobierz wszystkie wiersze, które maja inny status niżeli 53 
			public boolean apply(String[] data) {
				return !OMITTED.equals(data[Collector.STATUS])?true:false;
			}
		},repo);
		collector.collect();
		
		
		System.out.println("Wszystkie wiersze z pliku: " + collector.allNumLines());
		System.out.println("Ilość kategorii błędu: " + collector.filteredNumLines());
		System.out.println("Wszystkie przefiltrowane wiersze  : " + collector.filteredAllNumLines());
		
		Exporter exp = new FileExporter(outFile);
//		Exporter exp = new ConsoleExporter();
		
		//formatuj wynik i exportuj go np: na konsole
		Formatter formatter = new VelocityFormatter(collector,tmplFileName);
		formatter.formatTo(exp);
		
		
	}
	
}
