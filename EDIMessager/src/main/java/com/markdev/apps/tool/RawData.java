package com.markdev.apps.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RawData {

	private List<String> headerNames = new ArrayList<String>();
	private Map<Integer,List<String>> dane=new LinkedHashMap<Integer, List<String>>();  
	private int counter = 0;
	
	
	public RawData(String... kolumny){
		if (kolumny.length==0)
			throw new RuntimeException("");
		
		headerNames.add(kolumny[0]);
		
		for (int i=1; i < kolumny.length;i++)
			headerNames.add(kolumny[i]);
		
	}
	
	public void addData(String... linia){
		if (linia.length==0)  
			throw new RuntimeException("Długość nie jest poprawna");
		
	   List<String> lista = new ArrayList<String>(headerNames.size());
	   for (String pole: linia){
		   lista.add(pole);
	   }
	
	   dane.put(counter, lista);
	   counter++;
				
	}
	
	
	public String[] getLine(int number){
		if (number<0  ||  number>=counter)
			throw new RuntimeException("Aktualnie tabela posiada : " + counter + " wierszy");
		
		List<String> linia=  dane.get(number);
		
//		System.out.println(linia);
		
		String[] inner = new String[linia.size()];
		
		return linia.toArray(inner); 
	}
	
	
	
	
	
	public int linesNum(){
		return counter;
	}

	
	public String[] getHeaderNames(){
		
		String[] headerNamesTab = new String[headerNames.size()];
        headerNames.toArray(headerNamesTab);
		return headerNamesTab;
	}
	
	public String[] getColumnValues(int columnNumber){
		
		List<String> vals = new ArrayList<String>();
		
		
		if (columnNumber<0  ||  columnNumber>=headerNames.size())
			throw new RuntimeException("Aktualnie tabela posiada : " + headerNames.size() + " kolumn");
	   
		
		
		for (int i=0;i<counter;i++){
			String[] linia = getLine(i);
			vals.add(linia[columnNumber]);
		}

//		System.out.println(vals);
		
		String[] valsTab = new String[vals.size()];
		
		return vals.toArray(valsTab);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Table [counter=").append(counter).append("]").append("\n");
		builder.append(headerNames).append("\n");
	    builder.append(dane);
		return builder.toString();
	}
	
	
	
	
	
	
}
