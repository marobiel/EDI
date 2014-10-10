package com.markdev.apps.tool.impl;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import com.markdev.apps.tool.Collector;
import com.markdev.apps.tool.Exporter;
import com.markdev.apps.tool.Formatter;

public class VelocityFormatter implements Formatter {

	private Collector collector;
	private String template;
	
	
	public VelocityFormatter(Collector collector,String template) {
		this.collector=collector;
		this.template=template;
	}

	public void formatTo(Exporter exporter) {
		
		Properties props = new Properties();		
		props.setProperty("file.resource.loader.path", "/");
		VelocityEngine engine = new VelocityEngine(props);
		
		engine.init();
		
		
//		System.out.println(this.template);
		
//		Template tmpl = Velocity.getTemplate(this.template);
		Template tmpl = engine.getTemplate(this.template);
	
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("ILOSC_ERROR",collector.filteredAllNumLines());
		ctx.put("ILOSC",collector.allNumLines());
		
		
		
		Writer wr = new StringWriter();
		tmpl.merge(ctx, wr);
		
		exporter.export(wr);
	}

}
