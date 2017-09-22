package com.dbcompare.domain;

public class AllTables {

	private String owner;
	
	private String tableName;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllTables [owner=" + owner + ", tableName=" + tableName + "]";
	}
	
}
