package com.dbcompare.domain;

public class Configuration {

	private DBConfig sourceConfig;
	
	private DBConfig destConfig;

	/**
	 * @return the sourceConfig
	 */
	public DBConfig getSourceConfig() {
		return sourceConfig;
	}

	/**
	 * @param sourceConfig the sourceConfig to set
	 */
	public void setSourceConfig(DBConfig sourceConfig) {
		this.sourceConfig = sourceConfig;
	}

	/**
	 * @return the destConfig
	 */
	public DBConfig getDestConfig() {
		return destConfig;
	}

	/**
	 * @param destConfig the destConfig to set
	 */
	public void setDestConfig(DBConfig destConfig) {
		this.destConfig = destConfig;
	}
	
	
}
