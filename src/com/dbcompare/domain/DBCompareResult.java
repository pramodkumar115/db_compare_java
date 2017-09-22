package com.dbcompare.domain;

public class DBCompareResult {

	private SchemaCompareResult schemaResult;
	
	private DataCompareResult dataResult;

	/**
	 * @return the schemaResult
	 */
	public SchemaCompareResult getSchemaResult() {
		return schemaResult;
	}

	/**
	 * @param schemaResult the schemaResult to set
	 */
	public void setSchemaResult(SchemaCompareResult schemaResult) {
		this.schemaResult = schemaResult;
	}

	/**
	 * @return the dataResult
	 */
	public DataCompareResult getDataResult() {
		return dataResult;
	}

	/**
	 * @param dataResult the dataResult to set
	 */
	public void setDataResult(DataCompareResult dataResult) {
		this.dataResult = dataResult;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DBCompareResult [schemaResult=" + schemaResult + ", dataResult=" + dataResult + "]";
	}
	
	
}
