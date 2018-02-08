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
package com.js.rms9.common.beans;

import java.io.Serializable;

public class UserElementInfo implements Serializable{

	private String folderId;
	private String elementName;
	private String elementId;
	private String elementType;
	private String parentFolder;
	
	
	/**
	 * @return the folderId
	 */
	public String getFolderId() {
		return folderId;
	}


	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}


	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}


	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	/**
	 * @return the elementId
	 */
	public String getElementId() {
		return elementId;
	}


	/**
	 * @param elementId the elementId to set
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}


	/**
	 * @return the elementType
	 */
	public String getElementType() {
		return elementType;
	}


	/**
	 * @param elementType the elementType to set
	 */
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}


	/**
	 * @return the parentFolder
	 */
	public String getParentFolder() {
		return parentFolder;
	}


	/**
	 * @param parentFolder the parentFolder to set
	 */
	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserElementInfo [folderId=" + folderId + ", elementName=" + elementName + ", elementId=" + elementId
				+ ", elementType=" + elementType + ", parentFolder=" + parentFolder + "]";
	}
	

}
