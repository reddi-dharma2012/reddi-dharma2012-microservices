/**
 * Project Name: RMS v9 Foundation Development
 * @author TCS
 * Copyright @ 2017 - 2018, Sainsbury’s IT 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Sainsbury’s IT * @version 1.0, December 18, 2017
 * 
 * @since v1.0, December 18, 2017
 * 
 * *==============Modification History======================
 * Defect / Enhancement Reference ID: Sprint-2 added code
 * Description :Code for Registering Listeners
 * Author Name :TCS
 * Modification Date: 10/01/2018
 * Version No :v1.1.0
 * 
 * */
package com.js.rms9.common.config;

import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;
import org.springframework.web.util.Log4jConfigListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.js.rms9.common.constants.CommonConstants;
import com.js.rms9.common.filters.CORSFilter;

/**
 * Objective : WebInitializer class acts as deployment descriptor to enable the
 * following components.
 * 
 * 1. Registering Spring Configuration file. 2. Registering Servlet context. 3.
 * Registering Listers. 4. Registering Nohandler found exception.
 * 
 */
public class WebInitializer implements WebApplicationInitializer {

	@SuppressWarnings("deprecation")
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter(CommonConstants.SPRING_PROFILE_ACTIVE, CommonConstants.DEV);
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(Config.class);
		ctx.setServletContext(servletContext);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(CommonConstants.DISPATCHER_SERVLET_NAME,
				dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(CommonConstants.DISPATCHER_SERVLET_MAPPING);
      
		// Force HTTPS, and don't specify any roles for this constraint
		HttpConstraintElement forceHttpsConstraint = new HttpConstraintElement(
				ServletSecurity.TransportGuarantee.CONFIDENTIAL);
		ServletSecurityElement securityElement = new ServletSecurityElement(forceHttpsConstraint);

		// Add the security element to the servlet
		dispatcher.setServletSecurity(securityElement);

		servletContext.addListener(org.springframework.web.context.request.RequestContextListener.class);
		servletContext.addListener(org.springframework.web.util.Log4jConfigListener.class);
		servletContext.addFilter(CommonConstants.CORS_FILTER, CORSFilter.class).addMappingForUrlPatterns(null, false,
				CommonConstants.SLASH);

	}

}
