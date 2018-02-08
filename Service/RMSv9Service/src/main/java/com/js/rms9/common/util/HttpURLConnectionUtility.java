package com.js.rms9.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;

import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.exceptions.RMSException;

public class HttpURLConnectionUtility {
	
	public static JSONObject getRmsv9ApiResponse(String uri,String request,String methodType) throws RMSException{
		SSLCertificateUtil sSLCertificateUtil = new SSLCertificateUtil();
		JSONObject jsonResponse=null;
		try {
			sSLCertificateUtil.verifyCertificates();

			URL url = new URL(uri);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			ObjectMapper mapperObj = new ObjectMapper();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			String input = mapperObj.writeValueAsString(request);
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();
			JSONParser jsonParser = new JSONParser();
			jsonResponse= (JSONObject) jsonParser.parse(new InputStreamReader(httpConnection.getInputStream()));
			
		} catch (RestClientException | NoSuchAlgorithmException | KeyManagementException | ParseException
				| IOException ex) {
			throw new RMSException(ApplicationErrorMsgConstants.ERROR_INTEGRATION,ApplicationErrorMsgConstants.ERROR_INTE_MSG);
		}
		return jsonResponse;
	}
	
}
