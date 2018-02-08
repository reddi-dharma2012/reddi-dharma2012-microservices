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

import org.apache.log4j.rolling.RollingPolicy;
import org.apache.log4j.rolling.RolloverDescription;
import org.apache.log4j.rolling.TimeBasedRollingPolicy;


public class CustomTimeBasedRollingPolicy implements RollingPolicy {

    TimeBasedRollingPolicy timeBasedRollingPolicy = new TimeBasedRollingPolicy();

    /**
     * Set file name pattern.
     * 
     * @param fnp
     *            file name pattern.
     */
    public void setFileNamePattern(String fnp) {
        timeBasedRollingPolicy.setFileNamePattern(fnp);
    }

    /*
     * public void setActiveFileName(String fnp) {
     * timeBasedRollingPolicy.setActiveFileName(fnp);
     * }
     */

    /**
     * Get file name pattern.
     * 
     * @return file name pattern.
     */
    public String getFileNamePattern() {
        return timeBasedRollingPolicy.getFileNamePattern();
    }

    public RolloverDescription initialize(String file, boolean append)
            throws SecurityException {
        return timeBasedRollingPolicy.initialize(file, append);
    }

    public RolloverDescription rollover(String activeFile)
            throws SecurityException {
        return timeBasedRollingPolicy.rollover(activeFile);
    }

    public void activateOptions() {
        timeBasedRollingPolicy.activateOptions();
    }
}