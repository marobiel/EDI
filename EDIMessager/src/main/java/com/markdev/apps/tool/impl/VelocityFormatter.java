package com.markdev.apps.tool.impl;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

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
		Velocity.init();
		Template tmpl = Velocity.getTemplate(this.template);
	
		VelocityContext ctx = new VelocityContext();
		
		Writer wr = new StringWriter();
		tmpl.merge(ctx, wr);
		
		exporter.export(wr);
	}

}
