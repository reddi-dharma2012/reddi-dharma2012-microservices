package com.js.rms9.common.views;

import java.io.Serializable;

import com.js.rms9.common.beans.RMSUser;

public class RMSUserView extends RMSViewObject implements Serializable{
	
	/**
	 * 
	 * @param status
	 * @param erroCode
	 * @param errorMessage
	 */
	public RMSUserView(String status, String errorKey, String errorMessage){
		this.setStatus(status);
		this.setErrorKey(errorKey);
		this.setErrorDesc(errorMessage);
	}
	
	private RMSUser rmsUser;

	public RMSUser getRmsUser() {
		return rmsUser;
	}

	public void setRmsUser(RMSUser rmsUser) {
		this.rmsUser = rmsUser;
	}
	
	
}
