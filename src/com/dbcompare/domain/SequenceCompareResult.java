package com.dbcompare.domain;

public class SequenceCompareResult {

	private String sequenceName;
	
	private String compareMessage;
	
	private String status;

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getCompareMessage() {
		return compareMessage;
	}

	public void setCompareMessage(String compareMessage) {
		this.compareMessage = compareMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
