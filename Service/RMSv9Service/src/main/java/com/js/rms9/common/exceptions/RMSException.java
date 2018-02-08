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
 * */
package com.js.rms9.common.exceptions;

import org.apache.log4j.Logger;

import com.js.rms9.common.config.Config;

/**
 * Objective : This Class is to hold/wrap as generic exception object.
 *
 */
public class RMSException extends Exception {

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(Config.class.getName());

	private String[] errorMessages;
	private int severityType;
	private String key;

	public RMSException() {
		super();
	}

	public RMSException(String key, String errorMessages) {
		this(key, new String[] { errorMessages });
		log.error("**********Error Message*********: " + errorMessages);
	}

	/**
	 * 
	 * @param key,errorMessages
	 * @param validationMessages
	 */
	public RMSException(String key, String[] errorMessages) {
		this.key = key;
		this.errorMessages = errorMessages;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getErrorMessages() {
		return this.errorMessages;
	}

	public String getErrorMessage() {
		if (this.errorMessages != null && this.errorMessages.length > 0) {
			return this.errorMessages[0];
		}

		return null;
	}

	/**
	 * 
	 * @param severityType
	 */
	public void setSeverityType(int severityType) {
		this.severityType = severityType;
	}

	/**
	 * 
	 * @return
	 */
	public int getSeverityType() {
		return this.severityType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setErrorMessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}

}
