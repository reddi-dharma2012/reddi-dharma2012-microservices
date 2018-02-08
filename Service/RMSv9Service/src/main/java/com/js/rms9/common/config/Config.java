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
 * *============Modification History======================
 * Defect / Enhancement Reference ID: Sprint-2 added code
 * Description :Enabling cache
 * Author Name :TCS
 * Modification Date: 10/01/2018
 * Version No :v1.1.0
 * 
 * */
package com.js.rms9.common.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Objective : This is Spring Configuration class to specify the following.
 * 
 * 1. Component scan to enable classes in com.js.rms9 package as spring maintained beans. 
 * 
 * 2. Enables property resource configuration.
 * 
 * 3. Enables Web MVC feature of Spring framework. 
 * 
 * 4. Defines beans that need to initialized at the spring container startup. 
 */
@Configuration 
@ComponentScan("com.js.rms9")
@Import(PropertySourcesConfig.class)
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
	static Logger log = Logger.getLogger(Config.class.getName());
}