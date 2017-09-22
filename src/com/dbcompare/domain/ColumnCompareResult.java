package com.dbcompare.domain;

public class ColumnCompareResult {

	private String columnName;
	
	private String compareMessage;
	
	private String status;

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

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ColumnCompareResult [columnName=" + columnName + ", compareMessage=" + compareMessage + ", status="
				+ status + "]";
	}

	
}
