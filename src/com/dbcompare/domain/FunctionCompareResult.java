package com.dbcompare.domain;

public class FunctionCompareResult {

	private String functionName;
	
	private String compareMessage;

	private String status;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @return the compareMessage
	 */
	public String getCompareMessage() {
		return compareMessage;
	}

	/**
	 * @param compareMessage the compareMessage to set
	 */
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
