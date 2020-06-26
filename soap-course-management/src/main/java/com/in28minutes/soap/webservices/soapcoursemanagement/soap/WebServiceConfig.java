package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import java.util.Collections;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	//MessageDispatcherServlet
		//ApplicationContext
	//url -> /ws/*
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	// /ws/courses.wsdl 
		//PorType - CoursePort
		//Namesapce - http://in28minutes.com/courses
	//based on the schema -> course-details.xsd
	
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		//PorType - CoursePort
		definition.setPortTypeName("CoursePort");
		
		//Namesapce - http://in28minutes.com/courses
		definition.setTargetNamespace("http://in28minutes.com/courses");
		
		// /ws/courses.wsdl 
		definition.setLocationUri("/ws");
		
		//schema 
		definition.setSchema(coursesSchema);
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	
	//XsSecurityInterceptor
		//Callback handler -> SimplePasswordValidationCallbackHandler
		//Security Policy -> securityPolicy.xml
	//Interceptor.add --m> XsSecurityInterceptor
	@Bean
	public XwsSecurityInterceptor securityInterceptor(){
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		//Callback Handler -> SimplePasswordValidationCallbackHandler
		securityInterceptor.setCallbackHandler(callbackHandler());
		//Security Policy -> securityPolicy.xml
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}
	
	//Interceptors.add -> XwsSecurityInterceptor
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
}
