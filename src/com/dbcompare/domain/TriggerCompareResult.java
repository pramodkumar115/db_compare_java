package com.dbcompare.domain;

public class TriggerCompareResult {

	private String triggerName;
	
	private String compareMessage;

	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
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
	
	
}
