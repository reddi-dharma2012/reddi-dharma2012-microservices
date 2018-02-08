/**
 * Project Name: RMS v9 Foundation Development
 * @author TCS
 * Copyright (c) 2017 - 2018, Sainsbury’s IT 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Sainsbury’s IT
 * 
 * Author TCS.
 * Creation Date:29/12/2017
 */
package com.js.rms9.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.js.rms9.common.constants.CommonConstants;

/**
 * Objective : This Class defines/load of the below environment specific
 * properties based on active spring profile that is enabled.
 * 
 * 1. Production environment properties file
 * 
 * 2. Test environment properties file.
 * 
 * 3. Development environment properties file.
 * 
 * 4. Local environment properties file.
 */
@Configuration
public class PropertySourcesConfig {
	private static final Resource[] PROD_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(CommonConstants.PROD_PROPERTIES), };
	private static final Resource[] DEV_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(CommonConstants.DEV_PROPERTIES), };
	private static final Resource[] TEST_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(CommonConstants.TEST_PROPERTIES), };
	private static final Resource[] LOCAL_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(CommonConstants.LOCAL_PROPERTIES), };

	/**
	 * This Class is load local environment properties file when spring active
	 * profile is set to local.
	 */
	@Profile(CommonConstants.LOCAL)
	public static class LocalConfig {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(LOCAL_PROPERTIES);
			return pspc;
		}
	}

	/**
	 * This Class is load dev environment properties file when spring active
	 * profile is set to dev.
	 */
	@Profile(CommonConstants.DEV)
	public static class DevConfig {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(DEV_PROPERTIES);
			return pspc;
		}
	}

	/**
	 * This Class is load test environment properties file when spring active
	 * profile is set to test.
	 */
	@Profile(CommonConstants.TEST)
	public static class TestConfig {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(TEST_PROPERTIES);
			return pspc;
		}
	}

	/**
	 * This Class is load prod environment properties file when spring active
	 * profile is set to prod.
	 */
	@Profile(CommonConstants.PROD)
	public static class ProdConfig {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(PROD_PROPERTIES);
			return pspc;
		}
	}

}