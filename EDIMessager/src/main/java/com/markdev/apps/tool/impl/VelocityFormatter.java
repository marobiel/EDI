package com.markdev.apps.tool.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.markdev.apps.tool.Collector;
import com.markdev.apps.tool.Exporter;
import com.markdev.apps.tool.Formatter;

public class VelocityFormatter implements Formatter {

	private Collector collector;
	private String template;
	private Properties props;
	
	
	public VelocityFormatter(Collector collector,String template, Properties props) {
		this.collector=collector;
		this.template=template;
		this.props=props;
	}

	public void formatTo(Exporter exporter) {
		
		Properties props = new Properties();		
		props.setProperty("file.resource.loader.path", "/");
		VelocityEngine engine = new VelocityEngine(props);
		
		engine.init();
		
		Template tmpl = engine.getTemplate(this.template,"UTF-8");
	
		VelocityContext ctx = new VelocityContext();
		
		populateContext(ctx);
		
		
		
		Writer wr = new StringWriter();
		tmpl.merge(ctx, wr);
		
		exporter.export(wr);
	}

	private void populateContext(VelocityContext ctx) {
		ctx.put("ILOSC_ERROR",collector.filteredAllNumLines());
		ctx.put("ILOSC",collector.allNumLines());
		ctx.put("keyIterator", collector.iterator());
		ctx.put("collector", collector);
		
		boolean showdetails =  Boolean.parseBoolean(props.getProperty("showdetails","false"));		
		ctx.put("showdetails", showdetails);
	
		ctx.put("props", props);
		
	}

}
