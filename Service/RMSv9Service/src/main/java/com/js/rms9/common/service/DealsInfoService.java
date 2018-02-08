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
package com.js.rms9.common.service;

import com.js.rms9.common.views.DealInfoView;

/**
 * Objective : This interface holds the fixed deals CRUD operation abstract
 * methods.
 *
 */
public interface DealsInfoService {
	/**
	 * This is Abstract method to handle deals info.
	 * 
	 * @param userId
	 * @return
	 */
	public DealInfoView getDealsInfo(String userId);
}
