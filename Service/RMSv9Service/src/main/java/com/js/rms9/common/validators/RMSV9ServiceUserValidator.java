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

package com.js.rms9.common.validators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import com.js.rms9.common.beans.RMSUser;
import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.views.RMSUserRolesView;

@Component
@Profile({ "local", "dev", "test", "prod" })
public class RMSV9ServiceUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		return RMSUser.class.isAssignableFrom(obj);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		RMSUser rmsUser = (RMSUser) obj;

		if (rmsUser == null) {
			errors.rejectValue("RMSUSER", ApplicationErrorMsgConstants.ERROR_INVALID_REQUEST,
					ApplicationErrorMsgConstants.ERROR_INVALID_REQUEST_MSG);
			return;
		}
		if (rmsUser != null && StringUtils.isBlank(rmsUser.getUserId())) {
			errors.rejectValue("RMSUSER_ID", ApplicationErrorMsgConstants.ERROR_INVALID_REQUEST,
					ApplicationErrorMsgConstants.ERROR_INVALID_USER);
			return;

		}
		if (rmsUser != null && StringUtils.isBlank(rmsUser.getUserId())) {
			errors.rejectValue("RMSUSER_PASSWORD", ApplicationErrorMsgConstants.ERROR_INVALID_REQUEST,
					ApplicationErrorMsgConstants.ERROR_INVALID_PASSWORD);
			return;

		}
	}

	public RMSUserRolesView validateRMSUser(RMSUser user) {
		Map<String, String> map = new HashMap<>();
		MapBindingResult err = new MapBindingResult(map, String.class.getName());
		this.validate(user, err);
		if (err.hasErrors()) {
			return new RMSUserRolesView(ApplicationErrorMsgConstants.ERROR_MSG, err.getFieldErrors().get(0).getCode(),
					err.getFieldErrors().get(0).getDefaultMessage());
		} else {
			return null;
		}
	}

}