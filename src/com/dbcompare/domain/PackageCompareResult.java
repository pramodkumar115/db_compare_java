package com.dbcompare.domain;

import java.util.List;

public class PackageCompareResult {

	private String packageName;
	
	private List<ProcedureCompareResult> itemsResults;
	
	private String compareMessage;
	
	private String status;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<ProcedureCompareResult> getItemsResults() {
		return itemsResults;
	}

	public void setItemsResults(List<ProcedureCompareResult> itemsResults) {
		this.itemsResults = itemsResults;
	}

	public String getCompareMessage() {
		return compareMessage;
	}

	public void setCompareMessage(String compareMessage) {
		this.compareMessage = compareMessage;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
