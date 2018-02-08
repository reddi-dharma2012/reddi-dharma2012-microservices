package com.js.rms9.common.views;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RMSUserRolesView extends RMSViewObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> userRoles;
	private String userId;
	/**
	 * Returns userId property.
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets userId property.
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * 
	 * @param status
	 * @param erroCode
	 * @param errorMessage
	 */
	public RMSUserRolesView(String status, String errorKey, String errorMessage) {
		this.setStatus(status);
		this.setErrorKey(errorKey);
		this.setErrorDesc(errorMessage);
	}

	public RMSUserRolesView() {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RMSUserRolesView [userRoles=" + userRoles + ", userId=" + userId + "]";
	}

}
