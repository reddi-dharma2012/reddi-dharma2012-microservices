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
import java.util.List;

public class UserFolderInfo implements Serializable{
	
	private int treeDepth;
	
	private String foldername;
	
	private String folderId;
	
	private String parentFolder;
	
	private List<String> elementList;
	
	

	/**
	 * @return the treeDepth
	 */
	public int getTreeDepth() {
		return treeDepth;
	}



	/**
	 * @param treeDepth the treeDepth to set
	 */
	public void setTreeDepth(int treeDepth) {
		this.treeDepth = treeDepth;
	}



	/**
	 * @return the foldername
	 */
	public String getFoldername() {
		return foldername;
	}



	/**
	 * @param foldername the foldername to set
	 */
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}



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
		return "UserFolderInfo [treeDepth=" + treeDepth + ", foldername=" + foldername + ", folderId=" + folderId
				+ ", parentFolder=" + parentFolder + ",elementList=[" + elementList + "]]";
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((folderId == null) ? 0 : folderId.hashCode());
		result = prime * result + ((foldername == null) ? 0 : foldername.hashCode());
		result = prime * result + ((parentFolder == null) ? 0 : parentFolder.hashCode());
		result = prime * result + treeDepth;
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFolderInfo other = (UserFolderInfo) obj;
		if (folderId == null) {
			if (other.folderId != null)
				return false;
		} else if (!folderId.equals(other.folderId))
			return false;
		if (foldername == null) {
			if (other.foldername != null)
				return false;
		} else if (!foldername.equals(other.foldername))
			return false;
		if (parentFolder == null) {
			if (other.parentFolder != null)
				return false;
		} else if (!parentFolder.equals(other.parentFolder))
			return false;
		if (treeDepth != other.treeDepth)
			return false;
		return true;
	}



	/**
	 * @return the elementList
	 */
	public List<String> getElementList() {
		return elementList;
	}



	/**
	 * @param elementList the elementList to set
	 */
	public void setElementList(List<String> elementList) {
		this.elementList = elementList;
	}


}
