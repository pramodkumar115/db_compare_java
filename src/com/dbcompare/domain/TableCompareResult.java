package com.dbcompare.domain;

import java.util.List;

public class TableCompareResult {

	private String tableName;
	
	private String compareMessage;
	
	private List<ColumnCompareResult> columnResults;
	
	private List<IndexCompareResult> indexResults;
	
	private List<ConstraintCompareResult> constraintResults;
	
	private String status;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	 * @return the columnResults
	 */
	public List<ColumnCompareResult> getColumnResults() {
		return columnResults;
	}

	/**
	 * @param columnResults the columnResults to set
	 */
	public void setColumnResults(List<ColumnCompareResult> columnResults) {
		this.columnResults = columnResults;
	}

	/**
	 * @return the indexResults
	 */
	public List<IndexCompareResult> getIndexResults() {
		return indexResults;
	}

	/**
	 * @param indexResults the indexResults to set
	 */
	public void setIndexResults(List<IndexCompareResult> indexResults) {
		this.indexResults = indexResults;
	}

	/**
	 * @return the constraintResults
	 */
	public List<ConstraintCompareResult> getConstraintResults() {
		return constraintResults;
	}

	/**
	 * @param constraintResults the constraintResults to set
	 */
	public void setConstraintResults(List<ConstraintCompareResult> constraintResults) {
		this.constraintResults = constraintResults;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TableCompareResult [tableName=" + tableName + ", compareMessage=" + compareMessage + ", columnResults="
				+ columnResults + ", indexResults=" + indexResults + ", constraintResults=" + constraintResults
				+ ", status=" + status + "]";
	}
	
}
