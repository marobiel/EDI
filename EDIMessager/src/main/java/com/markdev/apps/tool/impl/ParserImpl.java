package com.markdev.apps.tool.impl;

import java.util.ArrayList;
import java.util.List;

import com.markdev.apps.tool.ColumnFilter;
import com.markdev.apps.tool.Parser;
import com.markdev.apps.tool.RawData;

public class ParserImpl implements Parser {

	private RawData tableData;
	private static final String DELIMER = "\t";
	private ColumnFilter filter;
	
	public ParserImpl(ColumnFilter filter){
		this.filter=filter;
	}
	
	public void parseHeader(String header){
    	String[] columnNames=  header.split(DELIMER);
        List<String> str = new ArrayList<String>();
    	
    	for (int i=0; i< columnNames.length;i++){
    		if (filter.apply(i)){
    			str.add(columnNames[i]);
    		}
    	}
   
    	
    	
    	String[] tab = new String[str.size()];
    	str.toArray(tab);
    	this.tableData = new RawData(tab);  
    }

	public void parseLine(String line, long counter) {
		
	   String[] fieldValues=  line.split(DELIMER);
	  
	   List<String> lista = new ArrayList<String>();
	   
	   for (int i=0;i<fieldValues.length;i++){
		   if (filter.apply(i)){
			   lista.add(fieldValues[i]);
		   }
	   }
	   
	   String[] arrayLista = new String[lista.size()];	   
	   lista.toArray(arrayLista);
	   
	   this.tableData.addData(arrayLista);
	   
	}

	public RawData data() {
		return tableData;
	}

}
