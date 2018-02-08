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
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.js.rms9.common.beans.DealInfo;
import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.constants.CommonConstants;
import com.js.rms9.common.service.DealsInfoService;
import com.js.rms9.common.util.SSLCertificateUtil;
import com.js.rms9.common.views.DealInfoView;

import java.util.List;

/**
 * 
 * Objective : This is service class which handles RMSAPI REST calls.
 */
@Service
@Profile({ "local", "dev", "test", "prod" })
public class DealsInfoServiceImpl implements DealsInfoService {

	@Value("${DEALS_API_URL}")
	private String dealsAPIURL;

	/**
	 * This method get the Fixed deals information on REST API call.
	 */
	@SuppressWarnings("unchecked")
	public DealInfoView getDealsInfo(String userId) {
		DealInfoView dealInfoView = new DealInfoView();
		
		SSLCertificateUtil sSLCertificateUtil = new SSLCertificateUtil();

		try {
			sSLCertificateUtil.verifyCertificates();

			StringBuilder urlString = new StringBuilder();
			urlString.append(dealsAPIURL);
			urlString.append(userId);

			URL url = new URL(urlString.toString());
			URLConnection con = url.openConnection();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(con.getInputStream()));

			dealInfoView.setData((List<DealInfo>) jsonObject.get(CommonConstants.DATA));
		} catch (RestClientException | NoSuchAlgorithmException | KeyManagementException | ParseException | IOException ex) {
			dealInfoView.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
			dealInfoView.setErrorKey(ApplicationErrorMsgConstants.ERROR_INTEGRATION);
			dealInfoView.setErrorDesc(ApplicationErrorMsgConstants.ERROR_INTE_MSG);
	} 

		return dealInfoView;
	}

	
}
