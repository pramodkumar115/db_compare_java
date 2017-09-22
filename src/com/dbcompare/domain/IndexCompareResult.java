package com.dbcompare.domain;

public class IndexCompareResult {

	private String indexName;
	
	private String compareMessage;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
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
		return "IndexCompareResult [indexName=" + indexName + ", compareMessage=" + compareMessage + "]";
	}
	
	
}
