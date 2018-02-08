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

import java.io.Serializable;
import java.util.List;

import com.js.rms9.common.beans.UserFolderInfo;
/**
 * Objective : This class holds JS Menu Tree  info. 
 *
 */
public class JSMenuTreeView extends RMSViewObject implements Serializable {

	public String  userId;
	
	public  List<UserFolderInfo> folderList;

	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the folderList
	 */
	public List<UserFolderInfo> getFolderList() {
		return folderList;
	}
	/**
	 * @param folderList the folderList to set
	 */
	public void setFolderList(List<UserFolderInfo> folderList) {
		this.folderList = folderList;
	}
	/**
	 * 
	 * @param status
	 * @param erroCode
	 * @param errorMessage
	 */
	public JSMenuTreeView(String status, String errorKey, String errorMessage){
		this.setStatus(status);
		this.setErrorKey(errorKey);
		this.setErrorDesc(errorMessage);
	}
	public JSMenuTreeView(){
		
	}
}
