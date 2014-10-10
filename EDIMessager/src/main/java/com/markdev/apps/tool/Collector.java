package com.markdev.apps.tool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Collector {

	
	public static final int STATUS=2;
	public static final int KEY=5;
	public static final int PARAM1=6;
	public static final int PARAM2=7;
	public static final int PARAM3=8;
	public static final int PARAM4=9;
	
	private RawData rawData;
    private Filter rowFilter;
	
	private Map<String, CollectedData> aggregatedData = new LinkedHashMap<String, CollectedData>();
	

	public Collector(RawData rawData,Filter filter) {
		super();
		this.rawData = rawData;
		this.rowFilter=filter;
	}

	
	
	
	
	public void collect(){
		
		
	  long length = rawData.linesNum();
	  
	  for (int i=0; i< length;i++){
		  String[] line = rawData.getLine(i);
		  if (this.rowFilter.apply(line)){
			  if (aggregatedData.containsKey(line[KEY])){
				  
				  CollectedData data=aggregatedData.get(line[KEY]);
				  data.addLine(i, modifyLine(line));
			  }
			  else{
				  
				  CollectedData data=new CollectedData(line[KEY]);
				  aggregatedData.put(line[KEY],data );
				  data.addLine(i, modifyLine(line));
			  } 
		  }		  		 
	  }	
	}
	
	private String[]  modifyLine(String[] line) {
		
		String[] copiedLine = copyArray(line);
		
		
		if (line[KEY].contains("&1")){
			copiedLine[KEY]=copiedLine[KEY].replaceAll("&1", line[PARAM1]);
		}
		
		if (line[KEY].contains("&2")){
			copiedLine[KEY]=copiedLine[KEY].replaceAll("&2", line[PARAM2]);
		}
		
		if (line[KEY].contains("&3")){
			copiedLine[KEY]=copiedLine[KEY].replaceAll("&3", line[PARAM3]);
		}
		
		if (line[KEY].contains("&4")){
			copiedLine[KEY]=copiedLine[KEY].replaceAll("&4", line[PARAM4]);
		}
			
		if (line[KEY].contains("&")){
			copiedLine[KEY]=copiedLine[KEY].replaceAll("&", line[PARAM1]);	
		}
		
		return copiedLine;
		
	}


	private String[] copyArray(String[] line) {
		String[] copied = new String[line.length];
		for (int i=0;i<line.length;i++){
			copied[i]=line[i];
		}
		
		return copied;
		
	}


    public Iterator<String> iterator(){    	
    	return aggregatedData.keySet().iterator();
    }
	
    public CollectedData get(String key){
    	return aggregatedData.get(key);
    }
    
    public int filteredNumLines(){
    	return aggregatedData.size();
    }
    
    public int filteredAllNumLines(){
    	Iterator<String> iter = iterator();
    	int alllines = 0;
    	while (iter.hasNext()){
    		CollectedData data = aggregatedData.get(iter.next());
    		alllines = alllines + data.linesNum();
    	}
    	return alllines;
    }
    
    public int allNumLines(){
    	return rawData.linesNum();
    }
    
    
	public int numLinesFor(String key){
		if (aggregatedData.containsKey(key)){
			CollectedData data =aggregatedData.get(key);
			return data.linesNum();
		}
	    throw new RuntimeException("Wartośc nie istnieje");
	}
	
	
}
