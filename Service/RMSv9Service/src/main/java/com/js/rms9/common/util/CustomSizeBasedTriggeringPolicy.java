/**
 * Project Name: RMS v9 Foundation Development
 * @author TCS
 * Copyright @ 2017 - 2018, Sainsbury’s IT 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Sainsbury’s IT * @version 1.0, January 10, 2018
 * 
 * @since v1.0, January, 2018 (Sprint 2)
 * 
 * */

package com.js.rms9.common.util;

import org.apache.log4j.Appender;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.rolling.TriggeringPolicy;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

/**
 * Copy of org.apache.log4j.rolling.SizeBasedTriggeringPolicy but able to accept
 * a human-friendly value for maximumFileSize, eg. "10MB"
 * 
 * Note that sub-classing SizeBasedTriggeringPolicy is not possible because that
 * class is final
 */
public class CustomSizeBasedTriggeringPolicy implements TriggeringPolicy, OptionHandler {

	/**
	 * Rollover threshold size in bytes.
	 */
	private long maximumFileSize = 10 * 1024 * 1024; // let 10 MB the default

	// max size

	/**
	 * Set the maximum size that the output file is allowed to reach before
	 * being rolled over to backup files.
	 * 
	 * &lt;p&gt; In configuration files, the &lt;b&gt;MaxFileSize&lt;/b&gt;
	 * option takes an long integer in the range 0 - 2^63. You can specify the
	 * value with the suffixes "KB", "MB" or "GB" so that the integer is
	 * interpreted being expressed respectively in kilobytes, megabytes or
	 * gigabytes. For example, the value "10KB" will be interpreted as 10240.
	 * 
	 * @param value
	 *            the maximum size that the output file is allowed to reach
	 */
	public void setMaxFileSize(String value) {
		maximumFileSize = OptionConverter.toFileSize(value, maximumFileSize + 1);
	}

	public long getMaximumFileSize() {
		return maximumFileSize;
	}

	public void setMaximumFileSize(long maximumFileSize) {
		this.maximumFileSize = maximumFileSize;
	}

	public void activateOptions() {
	}

	public boolean isTriggeringEvent(Appender appender, LoggingEvent event, String filename, long fileLength) {

		boolean result = (fileLength >= maximumFileSize);

		if (result) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
		}

		return result;
	}
}
