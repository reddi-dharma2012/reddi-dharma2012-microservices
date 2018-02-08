
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
import com.js.rms9.common.beans.UserFolderInfo;
import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.exceptions.RMSException;
import com.js.rms9.common.service.RMSV9ServiceJSMenuTreeService;
import com.js.rms9.common.util.HttpURLConnectionUtility;
import com.js.rms9.common.util.SSLCertificateUtil;
import com.js.rms9.common.views.JSMenuTreeView;
@Service
@Profile({ "local", "dev", "test", "prod" })
public class RMSV9ServiceJSMenuTreeServiceImpl implements RMSV9ServiceJSMenuTreeService {

	
	@Value("${JS_MENUTREE_API_URL}")
	private String jsMenuTreeUrl;

	@SuppressWarnings("unchecked")
	@Override
	public JSMenuTreeView getJSMenuTreeStructure(RMSUser user) {
		JSMenuTreeView jsenuTreeView = new JSMenuTreeView();
		SSLCertificateUtil sSLCertificateUtil = new SSLCertificateUtil();
		try {
			sSLCertificateUtil.verifyCertificates();
			URL url = new URL(jsMenuTreeUrl);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			ObjectMapper mapperObj = new ObjectMapper();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			String input = mapperObj.writeValueAsString(user);
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser
					.parse(new InputStreamReader(httpConnection.getInputStream()));
			String status = (String) jsonObject.get(ApplicationErrorMsgConstants.STATUS_CODE);
			jsenuTreeView.setStatus(status);
			if (status.contentEquals(ApplicationErrorMsgConstants.SUCCESS_MSG)) {
				jsenuTreeView.setStatus(ApplicationErrorMsgConstants.SUCCESS_MSG);
				jsenuTreeView.setUserId((String)jsonObject.get("userId"));
				jsenuTreeView.setFolderList((List<UserFolderInfo>) jsonObject.get("folderList"));
			} else {
				jsenuTreeView.setErrorKey((String) jsonObject.get(ApplicationErrorMsgConstants.ERROR_KEY));
				jsenuTreeView.setErrorDesc((String) jsonObject.get(ApplicationErrorMsgConstants.ERROR_DESC));
			}
		} catch (RestClientException | NoSuchAlgorithmException | KeyManagementException | ParseException
				| IOException ex) {
			jsenuTreeView.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
			jsenuTreeView.setErrorKey(ApplicationErrorMsgConstants.ERROR_INTEGRATION);
			jsenuTreeView.setErrorDesc(ApplicationErrorMsgConstants.ERROR_INTE_MSG);
		}
		return jsenuTreeView;
	}
}
