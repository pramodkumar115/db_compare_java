package com.dbcompare.domain;

import java.util.List;

public class SchemaCompareResult {

	private List<TableCompareResult> tableResultList;
	
	private List<PackageCompareResult> packageResultList;
	
	private List<ProcedureCompareResult> procedureResultList;
	
	private List<FunctionCompareResult> functionResultList;
	
	private List<SequenceCompareResult> sequenceResultList;
	
	private List<TriggerCompareResult> triggerResultList;
	
	private List<ViewCompareResult> viewResultList;

	/**
	 * @return the tableResultList
	 */
	public List<TableCompareResult> getTableResultList() {
		return tableResultList;
	}

	/**
	 * @param tableResultList the tableResultList to set
	 */
	public void setTableResultList(List<TableCompareResult> tableResultList) {
		this.tableResultList = tableResultList;
	}

	/**
	 * @return the packageResultList
	 */
	public List<PackageCompareResult> getPackageResultList() {
		return packageResultList;
	}

	/**
	 * @param packageResultList the packageResultList to set
	 */
	public void setPackageResultList(List<PackageCompareResult> packageResultList) {
		this.packageResultList = packageResultList;
	}

	/**
	 * @return the procedureResultList
	 */
	public List<ProcedureCompareResult> getProcedureResultList() {
		return procedureResultList;
	}

	/**
	 * @param procedureResultList the procedureResultList to set
	 */
	public void setProcedureResultList(List<ProcedureCompareResult> procedureResultList) {
		this.procedureResultList = procedureResultList;
	}

	/**
	 * @return the functionResultList
	 */
	public List<FunctionCompareResult> getFunctionResultList() {
		return functionResultList;
	}

	/**
	 * @param functionResultList the functionResultList to set
	 */
	public void setFunctionResultList(List<FunctionCompareResult> functionResultList) {
		this.functionResultList = functionResultList;
	}

	/**
	 * @return the sequenceResultList
	 */
	public List<SequenceCompareResult> getSequenceResultList() {
		return sequenceResultList;
	}

	/**
	 * @param sequenceResultList the sequenceResultList to set
	 */
	public void setSequenceResultList(List<SequenceCompareResult> sequenceResultList) {
		this.sequenceResultList = sequenceResultList;
	}

	/**
	 * @return the triggerResultList
	 */
	public List<TriggerCompareResult> getTriggerResultList() {
		return triggerResultList;
	}

	/**
	 * @param triggerResultList the triggerResultList to set
	 */
	public void setTriggerResultList(List<TriggerCompareResult> triggerResultList) {
		this.triggerResultList = triggerResultList;
	}

	/**
	 * @return the viewResultList
	 */
	public List<ViewCompareResult> getViewResultList() {
		return viewResultList;
	}

	/**
	 * @param viewResultList the viewResultList to set
	 */
	public void setViewResultList(List<ViewCompareResult> viewResultList) {
		this.viewResultList = viewResultList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchemaCompareResult [tableResultList=" + tableResultList + ", procedureResultList="
				+ procedureResultList + ", sequenceResultList=" + sequenceResultList + ", triggerResultList="
				+ triggerResultList + ", viewResultList=" + viewResultList + "]";
	}

	
	
}
