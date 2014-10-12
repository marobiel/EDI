package com.markdev.apps.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CollectedData {

	private String key;
	private int amount = 0;

	private Map<Integer, String[]> linesWithNumber = new LinkedHashMap<Integer, String[]>();
	private String desc;

	public CollectedData(String key, String opis) {
		super();
		this.key = key;
		this.desc = opis;

	}

	public void addLine(int rowNumber, String[] line) {
		linesWithNumber.put(rowNumber, line);
		amount = amount + 1;
	}

	public int linesNum() {
		return amount;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectedData other = (CollectedData) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CollectedData [key=").append(key).append(", amount=")
				.append(amount).append(", desc=").append(desc)
				.append(", linesWithNumber=").append(linesWithNumber)
				.append("]");
		return builder.toString();
	}

	public String toHtmlString(Properties props) {

		String fields = props.getProperty("headersNum");
		boolean isSetProperty = (fields != null);
		String[] lista = fields.split(",");
		List<String> values = new ArrayList<String>();

		if (isSetProperty) {
			values = Arrays.asList(lista);
		}

		StringBuilder builder = new StringBuilder();

		for (Integer rowNum : linesWithNumber.keySet()) {
			builder.append("<tr>");
			String[] linia = linesWithNumber.get(rowNum);

			if (!isSetProperty) {

				for (String field : linia) {
					builder.append("<td>");
					builder.append(field);
					builder.append("</td>");
				}
			} else {
				for (int i = 0; i < linia.length; i++) {
					if (values.contains(i+1+"")) {
						builder.append("<td>");
						builder.append(linia[i]);
						builder.append("</td>");
					}
				}
			}
			builder.append("</tr>");
		}

		return builder.toString();
	}

}
