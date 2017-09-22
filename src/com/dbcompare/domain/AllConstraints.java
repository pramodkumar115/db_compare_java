package com.dbcompare.domain;

public class AllConstraints {
	private String owner;
	
	private String constraintName;
	
	private String columnName;
	
	private String tableName;

	private String constraintType;
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
	 * @return the constraintName
	 */
	public String getConstraintName() {
		return constraintName;
	}

	/**
	 * @param constraintName the constraintName to set
	 */
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
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
	 * @return the constraintType
	 */
	public String getConstraintType() {
		return constraintType;
	}

	/**
	 * @param constraintType the constraintType to set
	 */
	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}
	
}
