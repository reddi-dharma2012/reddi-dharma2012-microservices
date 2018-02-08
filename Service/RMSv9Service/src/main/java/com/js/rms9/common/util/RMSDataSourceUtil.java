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

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.exceptions.RMSException;

@Component
public class RMSDataSourceUtil {

	@Autowired
	private HttpServletRequest request;

	@Value("${DBASE_CLS_NAME}")
	private String dbClassName;

	@Value("${DBASE_URL}")
	private String dbUrl;
	
	public JdbcTemplate getJdbcTemplate() {

		String userId = request.getHeader("USER_ID");
		String passWd = request.getHeader("PASSWORD");
		return new JdbcTemplate(getDriverManagerDataSource(userId, passWd));

	}

	/**
	 * This method creates data source instance  
	 * @param userName
	 * @param passWord
	 * @return DriverManagerDataSource
	 */
	public DriverManagerDataSource getDriverManagerDataSource(String userName, String passWord) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(dbClassName);
		ds.setUrl(dbUrl);
		ds.setUsername(userName);
		ds.setPassword(passWord);
		return ds;
	}

	/**
	 * This method creates data source instance  
	 * @param userName
	 * @param passWord
	 * @return SingleConnectionDataSource
	 */
	public SingleConnectionDataSource getSingleConnectionDatesource(String userName, String passWord) {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		ds.setDriverClassName(dbClassName);
		ds.setUrl(dbUrl);
		ds.setUsername(userName);
		ds.setPassword(passWord);
		return ds;
	}
   
	/**
	 *  This method closed data source connection.
	 * @param jdbcTemplate
	 * @throws RMSException
	 */
	public static void closeDataSourceConnection(JdbcTemplate jdbcTemplate) throws RMSException {
		DriverManagerDataSource ds = (DriverManagerDataSource) jdbcTemplate.getDataSource();
		try {
			if (ds != null)
				ds.getConnection().close();
		} catch (SQLException e) {
			throw new RMSException(ApplicationErrorMsgConstants.DB_OP_ERROR,
					"DB Operation Error: While Closing resources");

		}
	}

}
