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
package com.js.rms9.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.js.rms9.common.constants.ApplicationConstants;
import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.exceptions.RMSException;
import com.js.rms9.common.exceptions.RMSLambdaException;
import com.js.rms9.common.exceptions.RMSLockedException;
/**
 * Objective : This class handles the following operations.
 * 
 * 1. validates data base operation errors
 * 2. validates data base lock errors. 
 * 3. Closes data source connections. 
 * 
 *
 */

@Profile({"dev","test","prod","local"})
@Component
public class RMSDbUtility {
	
	static Logger log = Logger.getLogger(RMSDbUtility.class.getName());
	


	protected int numberOfRetries;
	protected long startTryTime;
	public static final String SUCCESS_OUT_PARAM = "success";
	public static final String ERROR_OUT_PARAM = "error";

	@Value("${DBASE_CLS_NAME}")
	private String dbClassName; 
	
	@Value("${DBASE_URL}")
	private String dbUrl;
	
	/**
	 *  This method closes the data source connection.
	 * @param jdbcTemplate
	 * @throws RMSException
	 */
	public static void closeDataSourceConnection(JdbcTemplate jdbcTemplate) throws RMSException{
		DriverManagerDataSource ds=(DriverManagerDataSource)jdbcTemplate.getDataSource();
		try {
			if(ds!=null)
			ds.getConnection().close();
		} catch (SQLException e) {
			throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR,
					"DB Operation Error: While Closing resources");
	
		}
	}
	/**
	 * This method is used to check for any errors from the callable statement.
	 * 
	 * @param stmt
	 * @throws RSSException
	 */
	public void checkForErrors(CallableStatement stmt) throws RMSException {
		try {
			if (stmt.getInt(1) == 0) {
				if (stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.LOCKED) > -1
						|| stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.ERROR_CODE) > -1
						|| stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.NOWAIT) > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS, ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS_MSG);
				}

				throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, stmt.getString(2));
			}
		} catch (SQLException e) {
			throw new RMSException(String.valueOf(e.getErrorCode()), e.getMessage());
		}
	}

	
	/**
	 * This method is used to check for any errors from the result set of
	 * callable statement.
	 * 
	 * @param resultMap
	 * @throws RMSException
	 */
	public void checkForProcErrors(Map<String, Object> resultMap) throws RMSException {

		if (StringUtils.isNotBlank((String) resultMap.get(ERROR_OUT_PARAM))) {
			 log.error(":::Error from Procedure:::"+(String) resultMap.get(ERROR_OUT_PARAM));
				if (((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.LOCKED) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.ERROR_CODE) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.NOWAIT) > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS, ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS_MSG);
				}
				if (((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("cursor") > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("SQL") > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("function") > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, ApplicationErrorMsgConstants.DB_OP_ERROR_MSG);
				}
				throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, (String) resultMap.get(ERROR_OUT_PARAM));
			
		}
	}
	/**
	 * This method is used to check for any errors from the result set of
	 * callable statement.
	 * 
	 * @param resultMap
	 * @throws RMSException
	 */
	public void checkForErrors(Map<String, Object> resultMap) throws RMSException {

		if (((Integer) resultMap.get(SUCCESS_OUT_PARAM)).intValue() == 0 && StringUtils.isNotBlank((String) resultMap.get(ERROR_OUT_PARAM))) {
			 log.error(":::Error from package:::"+(String) resultMap.get(ERROR_OUT_PARAM));
				if (((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.LOCKED) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.ERROR_CODE) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.NOWAIT) > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS, ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS_MSG);
				}
				if (((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("cursor") > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("SQL") > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf("function") > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, ApplicationErrorMsgConstants.DB_OP_ERROR_MSG);
				}
				throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, (String) resultMap.get(ERROR_OUT_PARAM));
			
		}
	}
	/**
	 * This method is used to check for any DB lock errors from the result set of
	 * callable statement.
	 * 
	 * @param resultMap
	 * @throws RMSException
	 */
	public void checkForLockError(Map<String, Object> resultMap) throws RMSException, RMSLockedException {

		if (((Integer) resultMap.get(SUCCESS_OUT_PARAM)).intValue() == 0 && StringUtils.isNotBlank((String) resultMap.get(ERROR_OUT_PARAM))) {
			log.error(":::Error from package:::"+(String) resultMap.get(ERROR_OUT_PARAM));
				if (((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.LOCKED) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.ERROR_CODE) > -1
						|| ((String) resultMap.get(ERROR_OUT_PARAM)).toLowerCase().indexOf(ApplicationConstants.NOWAIT) > -1) {
					throw new RMSLockedException(new RMSException(ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS, ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS_MSG));
				}
				throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, (String) resultMap.get(ERROR_OUT_PARAM));
			
		}
	}

	/**
	 * This method is used to register output parameters to a callable
	 * statement.
	 * 
	 * @param CallableStatement
	 * @throws RMSException
	 */
	protected void registerErrorOutputParams(CallableStatement stmt)  {
		try {
			stmt.registerOutParameter(1, Types.INTEGER); // 1 = success, 0 =
			// error
			stmt.setNull(1, Types.INTEGER);

			stmt.registerOutParameter(2, Types.VARCHAR); // Error message
			stmt.setNull(2, Types.VARCHAR);
		} catch (SQLException e) {
			throw new RMSLambdaException(new RMSException(String.valueOf(e.getErrorCode()), e.getMessage()));
		}
	}

	
	/**
	 * This method is used to check for any errors from the callable statement.
	 * 
	 * @param stmt
	 * @throws RMSException
	 */
	public void checkForPackageErrors(CallableStatement stmt) throws RMSException {
		try {
			if (stmt.getInt(3) == 0) {
				if (stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.LOCKED) > -1
						|| stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.ERROR_CODE) > -1
						|| stmt.getString(2).toLowerCase().indexOf(ApplicationConstants.NOWAIT) > -1) {
					throw new RMSException(ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS, ApplicationErrorMsgConstants.RECORD_LOCKED_BY_RMS_MSG);
				}

				throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR, stmt.getString(2));
			}
		} catch (SQLException e) {
			throw new RMSException(String.valueOf(e.getErrorCode()), e.getMessage());
		}
	}
	
	/**
	 * This method is used to to close connection 
	 * 
	 * @param con
	 * @return void
	 * @throws RMSException
	 */
	public void closeConnection(Connection con) throws RMSException{
		try {
			if (con != null){ 
				con.close();
			}
		} catch (SQLException sqlException) {
			log.error("Error occurred while closing the connection");
			throw new RMSException(String.valueOf(sqlException.getErrorCode()), sqlException.getMessage());
			
		}
	}
	
	
}