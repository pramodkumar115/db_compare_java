package com.dbcompare.domain;

public class AllSequences {

	private String owner;
	
	private String sequenceName;
	
	private long minValue;
	
	private long maxValue;
	
	private long incrementBy;

	private long cacheSize;
	
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
	 * @return the sequenceName
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * @param sequenceName the sequenceName to set
	 */
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	/**
	 * @return the minValue
	 */
	public long getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public long getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the incrementBy
	 */
	public long getIncrementBy() {
		return incrementBy;
	}

	/**
	 * @param incrementBy the incrementBy to set
	 */
	public void setIncrementBy(long incrementBy) {
		this.incrementBy = incrementBy;
	}

	/**
	 * @return the cacheSize
	 */
	public long getCacheSize() {
		return cacheSize;
	}

	/**
	 * @param cacheSize the cacheSize to set
	 */
	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}
	
	
}
