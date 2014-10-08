package com.markdev.apps.tool;

public interface Parser {

	public void parseHeader(String readLine);

	public void parseLine(String line, long l);

	public  RawData data();
	
}
