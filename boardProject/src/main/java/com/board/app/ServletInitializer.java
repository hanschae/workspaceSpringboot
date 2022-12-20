package com.board.app;

import java.util.Collections;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BoardProjectApplication.class);
	}
	@Bean
	public ConfigurableServletWebServerFactory configurablesServletWebServerFactory() {
		return new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				super.postProcessContext(context);
				JspPropertyGroup jspPropertyGroup= new JspPropertyGroup();
				jspPropertyGroup.addUrlPattern("*.jsp");
				jspPropertyGroup.addUrlPattern("*.jspf");
				jspPropertyGroup.setPageEncoding("UTF-8");
				jspPropertyGroup.setScriptingInvalid("true");
				jspPropertyGroup.setTrimWhitespace("true");
				jspPropertyGroup.setDefaultContentType("text/html");
				
				JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor = new JspPropertyGroupDescriptorImpl(jspPropertyGroup);
				context.setJspConfigDescriptor(new JspConfigDescriptorImpl(Collections.singletonList(jspPropertyGroupDescriptor), Collections.emptyList()));
				
			}
		};
	}
}
