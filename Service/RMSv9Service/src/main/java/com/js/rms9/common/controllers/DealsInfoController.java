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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.rms9.common.constants.CommonConstants;
import com.js.rms9.common.service.DealsInfoService;
import com.js.rms9.common.views.DealInfoView;

/**
 * Objective : This class performs multiple CRUD operations on fixed deals. 1.
 * Fetches Fixed deals information list for a given userId
 */

@CrossOrigin
@RestController
@RequestMapping("/deals")
@Profile({ "local", "dev", "test", "prod" })
public class DealsInfoController {

	@Autowired
	private DealsInfoService dealsInfoService;

	@RequestMapping(value = "/getDeals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getDeals() {
		return CommonConstants.SUCCESS;
	}

	/**
	 * This method fetches the fixed deal records for the given userId, Return
	 * DealInfoView object.
	 * 
	 * @param userId
	 * @return DealInfoView
	 */
	@RequestMapping(value = "/getDeals/userId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DealInfoView getDealsInfo(@PathVariable("userId") String userId) {
		return dealsInfoService.getDealsInfo(userId.trim());
	}
}
