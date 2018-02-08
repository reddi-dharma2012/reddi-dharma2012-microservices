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
/**
 * 
 * Objective : This custom exception class is to handle DB locking exceptions.
 */
public class RMSLockedException extends Exception {
	private static final long serialVersionUID = 1L;
	final RMSException re;

	public RMSLockedException(RMSException re) {
		this.re = re;
	}

	public RMSException getRSSException() {
		return re;
	}

	public static RMSLockedException throwWrapped(RMSException re) throws RMSLockedException {
		throw new RMSLockedException(re);
	}
}
