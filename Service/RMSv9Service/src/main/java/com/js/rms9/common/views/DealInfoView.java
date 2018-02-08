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
package com.js.rms9.common.views;

import java.util.List;

import com.js.rms9.common.beans.DealInfo;
/**
 * Objective : This class holds Fixed deals info. 
 *
 */
public class DealInfoView extends RMSViewObject {

	private List<DealInfo> data;
    private int count;
	/**
	 * @return the data
	 */
	public List<DealInfo> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<DealInfo> data) {
		this.data = data;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
    
}
