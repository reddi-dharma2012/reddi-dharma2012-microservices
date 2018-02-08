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
package com.js.rms9.common.serviceimpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.js.rms9.common.beans.RMSUser;
import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.service.RMSV9ServiceAutheniticationService;
import com.js.rms9.common.util.SSLCertificateUtil;
import com.js.rms9.common.views.RMSUserRolesView;

@Service
@Profile({ "local", "dev", "test", "prod" })
public class RMSV9ServiceAutheniticationServiceImpl implements RMSV9ServiceAutheniticationService {

	/*
	 * @Override public RMSUserRolesView autheniticateRMSuser(RMSUser user) {
	 * RMSUserRolesView rmsUserRolesView = new RMSUserRolesView();
	 * rmsUserRolesView.setStatus(ApplicationErrorMsgConstants.SUCCESS_MSG); //
	 * Rest api call to intergration layer should go here. return
	 * rmsUserRolesView; }
	 */
	@Value("${USERAUTH_API_URL}")
	private String userAuthAPIURL;

	@SuppressWarnings("unchecked")
	public RMSUserRolesView autheniticateRMSuser(RMSUser user) {
		RMSUserRolesView rmsUserRolesView = new RMSUserRolesView();

		SSLCertificateUtil sSLCertificateUtil = new SSLCertificateUtil();

		try {
			sSLCertificateUtil.verifyCertificates();

			URL url = new URL(userAuthAPIURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			String input = getJsonData(user);

			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser
					.parse(new InputStreamReader(httpConnection.getInputStream()));

			String status = (String) jsonObject.get(ApplicationErrorMsgConstants.STATUS_CODE);
			rmsUserRolesView.setStatus(status);
			if (status.contentEquals(ApplicationErrorMsgConstants.SUCCESS_MSG)) {
				rmsUserRolesView.setUserRoles((List<String>) jsonObject.get("userRoles"));
				rmsUserRolesView.setUserId((String)jsonObject.get("userId"));
			} else {
				rmsUserRolesView.setErrorKey((String) jsonObject.get(ApplicationErrorMsgConstants.ERROR_KEY));
				rmsUserRolesView.setErrorDesc((String) jsonObject.get(ApplicationErrorMsgConstants.ERROR_DESC));
			}
		} catch (RestClientException | NoSuchAlgorithmException | KeyManagementException | ParseException
				| IOException ex) {
			rmsUserRolesView.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
			rmsUserRolesView.setErrorKey(ApplicationErrorMsgConstants.ERROR_INTEGRATION);
			rmsUserRolesView.setErrorDesc(ApplicationErrorMsgConstants.ERROR_INTE_MSG);
		}
		return rmsUserRolesView;
	}

	private String getJsonData(RMSUser user) {

		ObjectMapper mapperObj = new ObjectMapper();
		String result = null;
		try {
			// get object as a json string
			System.out.println(mapperObj.writeValueAsString(user));
			result = mapperObj.writeValueAsString(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
