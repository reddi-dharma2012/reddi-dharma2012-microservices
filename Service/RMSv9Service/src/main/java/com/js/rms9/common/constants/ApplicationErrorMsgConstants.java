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
package com.js.rms9.common.constants;

/**
 * Objective : This class holds error/warn message constants.
 *
 */
public final class ApplicationErrorMsgConstants {
	public static final int ERROR = 0;
	public static final int WARNING = 1;
	public static final int CANCELLABLE_WARNING = 2;
	public static final String SUCCESS_MSG = "SUCCESS";
	public static final String ERROR_MSG = "ERROR";
	public static final String WARN_MSG = "WARN";
	public static final String STATUS_CODE = "status";
	public static final String ERROR_KEY = "errorKey";
	public static final String ERROR_DESC = "errorDesc";


	public static final String DB_OP_ERROR = "error.db_op_error";
	public static final String DB_OP_ERROR_MSG = "Database operation error.";

	public static final String SQL_ERROR = "error.sql_error";
	public static final String RMS_PACKAGE_ERROR = "error.rms_package_error";
	public static final String SERIALIZED_DB_ERROR = "error.serialized_db_error";
	public static final String BLANK_USERID = "error.blankUserid";
	public static final String BLANK_USERID_MSG = "UserId cant be Blank";
	public static final String RECORD_LOCKED_BY_RMS = "error.record_locked_by_rms";
	public static final String RECORD_LOCKED_BY_RMS_MSG = "This record is locked by RMS. Please try again.";
	public static final String ERROR_USER_DETAIL_DATA_ACCESS = "error.userdatils.access";

	public static final String UNKNOWN_TECH_ERROR = "unknown.techerror";
	public static final String TECHNICAL_ERROR = "Technical Error";
	
	public static final String ERROR_AUTH = "error.auth";
	public static final String AUTH_FAILURE = "Authenitication failure";
	
	public static final String ERR_404 = "error.404";
	public static final String ERR_CODE_404_URL = "Error Code 404: Requested URL";
	
	public static final String NOT_FOUND = " not found";
	
	public static final String ERROR_INTEGRATION = "integration.error";
	
	public static final String ERROR_INTE_MSG = "Integration Error";
	//public static final String ERROR_USER_DETAIL_DATA_ACCESS="error.userdatils.access";
    public static final String  ERROR_INVALID_REQUEST="error.invalidrequest";
    public static final String  ERROR_INVALID_REQUEST_MSG="Invalid Request- User Id and Password cant be Blank";
	public static final String ERROR_INVALID_USER = "User Id can't be Blank";
	public static final String ERROR_INVALID_PASSWORD = "Password cant be Blank";
	
	
	
	private ApplicationErrorMsgConstants() {

	}
}
