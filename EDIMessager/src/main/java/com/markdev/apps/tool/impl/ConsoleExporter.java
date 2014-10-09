package com.markdev.apps.tool.impl;

import java.io.Writer;

import com.markdev.apps.tool.Exporter;

public class ConsoleExporter implements Exporter {

	public void export(Writer wr) {
		System.out.println(wr);
	}

}
