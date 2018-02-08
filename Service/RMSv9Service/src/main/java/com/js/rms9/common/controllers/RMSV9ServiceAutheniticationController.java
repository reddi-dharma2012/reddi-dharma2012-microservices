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
package com.js.rms9.common.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.rms9.common.beans.RMSUser;
import com.js.rms9.common.service.RMSV9ServiceAutheniticationService;
import com.js.rms9.common.validators.RMSV9ServiceUserValidator;
import com.js.rms9.common.views.RMSUserRolesView;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@Profile({ "local", "dev", "test", "prod" })
public class RMSV9ServiceAutheniticationController {

	@Autowired
	private RMSV9ServiceUserValidator rmsV9ServiceUserValidator;
	@Autowired
	private RMSV9ServiceAutheniticationService rmsV9ServiceAutheniticationService;
	
	@RequestMapping(value = "/autheniticateRMSUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RMSUserRolesView autheniticateRMSUser(@RequestBody RMSUser user) {
		RMSUserRolesView userRolesView = rmsV9ServiceUserValidator.validateRMSUser(user);
		if (userRolesView == null)
			return rmsV9ServiceAutheniticationService.autheniticateRMSuser(user);
		else
			return userRolesView;

	}
}
