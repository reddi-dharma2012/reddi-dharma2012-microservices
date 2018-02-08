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
package com.js.rms9.common.views;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Objective : This class holds Json response status information. 
 *
 */
public class RMSViewObject {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorKey;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorDesc;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String warningCode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String warningDesc;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorKey() {
		return errorKey;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getWarningCode() {
		return warningCode;
	}
	public void setWarningCode(String warningCode) {
		this.warningCode = warningCode;
	}
	public String getWarningDesc() {
		return warningDesc;
	}
	public void setWarningDesc(String warningDesc) {
		this.warningDesc = warningDesc;
	}

}
