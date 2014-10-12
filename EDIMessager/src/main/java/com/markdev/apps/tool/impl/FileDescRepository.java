package com.markdev.apps.tool.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileDescRepository extends DescRepository {

	public static final String DELIMER = "####";

	private Map<String, String> descriptions = new HashMap<String, String>();

	public FileDescRepository(File propsfile) {
		super();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(propsfile));
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] extractedLine = line.split(DELIMER);
				if (!descriptions.containsKey(extractedLine[0])) {
					descriptions.put(extractedLine[0], extractedLine[1]);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public String get(String key) {		
		return descriptions.containsKey(key)?descriptions.get(key):"!!!Brak opisu!!!";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDescRepository [descriptions=")
				.append(descriptions).append("]");
		return builder.toString();
	}

	
	
}
