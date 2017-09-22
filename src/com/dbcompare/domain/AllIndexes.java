package com.dbcompare.domain;

public class AllIndexes {
	
	private String owner;
	
	private String indexName;
	
	private String tableName;
	
	private String indexType;
	
	private String uniqueness;
	
	private String compression;
	
	private String columnName;

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
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
	 * @return the indexType
	 */
	public String getIndexType() {
		return indexType;
	}

	/**
	 * @param indexType the indexType to set
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	/**
	 * @return the uniqueness
	 */
	public String getUniqueness() {
		return uniqueness;
	}

	/**
	 * @param uniqueness the uniqueness to set
	 */
	public void setUniqueness(String uniqueness) {
		this.uniqueness = uniqueness;
	}

	/**
	 * @return the compression
	 */
	public String getCompression() {
		return compression;
	}

	/**
	 * @param compression the compression to set
	 */
	public void setCompression(String compression) {
		this.compression = compression;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}
