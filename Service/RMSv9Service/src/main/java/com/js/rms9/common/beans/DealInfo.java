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

/**
 * Objective : This class holds Fixed deals info. 
 *
 */
public class DealInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dealId;
	private String supplierId;
	private String supplierName;
	private String status;
	private String activeDate;
	private String closeDate;
	private String extRefNo;

	/**
	 * Returns dealId property.
	 * 
	 * @return the dealId
	 */
	public String getDealId() {
		return dealId;
	}

	/**
	 * Sets dealId property.
	 * 
	 * @param dealId
	 *            the dealId to set
	 */
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	/**
	 * Returns supplierId property.
	 * 
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * Sets supplierId property.
	 * 
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * Returns supplierName property.
	 * 
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * Sets supplierName property.
	 * 
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * Returns status property.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets status property.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns activeDate property.
	 * 
	 * @return the activeDate
	 */
	public String getActiveDate() {
		return activeDate;
	}

	/**
	 * Sets activeDate property.
	 * 
	 * @param activeDate
	 *            the activeDate to set
	 */
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * Returns closeDate property.
	 * 
	 * @return the closeDate
	 */
	public String getCloseDate() {
		return closeDate;
	}

	/**
	 * Sets closeDate property.
	 * 
	 * @param closeDate
	 *            the closeDate to set
	 */
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	/**
	 * Returns extRefNo property.
	 * 
	 * @return the extRefNo
	 */
	public String getExtRefNo() {
		return extRefNo;
	}

	/**
	 * Sets extRefNo property.
	 * 
	 * @param extRefNo
	 *            the extRefNo to set
	 */
	public void setExtRefNo(String extRefNo) {
		this.extRefNo = extRefNo;
	}

	@Override
	public String toString() {
		return "DealInfo [dealId=" + dealId + ", supplierId=" + supplierId + ", supplierName=" + supplierName
				+ ", status=" + status + ", activeDate=" + activeDate + ", closeDate=" + closeDate + ", extRefNo="
				+ extRefNo + "]";
	}

}
