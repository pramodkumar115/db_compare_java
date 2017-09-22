package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllTables;
import com.dbcompare.domain.ColumnCompareResult;
import com.dbcompare.domain.ConstraintCompareResult;
import com.dbcompare.domain.IndexCompareResult;
import com.dbcompare.domain.TableCompareResult;
import com.dbcompare.services.DDLHelper;

public class TableValidator extends Validator {
	public TableValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}
	
	public List<TableCompareResult> validate(){
		List<TableCompareResult> resultList = new ArrayList<>();
		String query = "select owner, table_name from all_tables where owner = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllTables> sourceTables = new ArrayList<>();
			List<AllTables> destTables = new ArrayList<>();

			while (sourceRes.next()) {
				AllTables sTable = new AllTables();
				sTable.setOwner(sourceRes.getString(1));
				sTable.setTableName(sourceRes.getString(2));
				sourceTables.add(sTable);
			}

			while (destRes.next()) {
				AllTables dTable = new AllTables();
				dTable.setOwner(destRes.getString(1));
				dTable.setTableName(destRes.getString(2));
				destTables.add(dTable);
			}
			for (AllTables sTable : sourceTables) {
				boolean sourcePresent = false;
				for (AllTables dTable : destTables) {
					if (sTable.getTableName().equalsIgnoreCase(dTable.getTableName())) {
						sourcePresent = true;
						TableCompareResult tResult = validateTable(sTable, dTable);
						if(tResult.getStatus() != null && !"".equals(tResult.getStatus())){
							resultList.add(tResult);
						}
						break;
					}
				}
				if (!sourcePresent) {
					TableCompareResult tResult = new TableCompareResult();
					tResult.setTableName(sTable.getTableName());
					StringBuilder message = new StringBuilder();
					tResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					message.append(DDLHelper.getDDL("TABLE", sTable.getTableName(), sourceCon));
					tResult.setCompareMessage(message.toString());
					resultList.add(tResult);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	private TableCompareResult validateTable(AllTables sourceTable, AllTables destTables) {
		if(sourceTable.getTableName().equals("APEX$_WS_HISTORY")){
			System.out.println(sourceTable.getTableName());
		}
		TableCompareResult result = new TableCompareResult();
		ColumnValidator cValidator = new ColumnValidator(sourceCon, destCon, sourceOwner, destOwner);
		IndexValidator iValidator = new IndexValidator(sourceCon, destCon, sourceOwner, destOwner);
		ConstraintValidator conValidator = new ConstraintValidator(sourceCon, destCon, sourceOwner, destOwner);
		
		List<ColumnCompareResult> cResults = cValidator.validate(sourceTable.getTableName());
		List<IndexCompareResult> iResults = iValidator.validate(sourceTable.getTableName());
		List<ConstraintCompareResult> conResults = conValidator.validate(sourceTable.getTableName());
		
		StringBuilder message = new StringBuilder();
		result.setCompareMessage(message.toString());
		result.setTableName(sourceTable.getTableName());
		if(cResults != null && cResults.size() > 0){
			result.setColumnResults(cResults);
			String status = result.getStatus();
			if(status == null){
				result.setStatus("COLUMN");
			}else{
				result.setStatus(status + ",COLUMN");
			}
			
		}
		if(iResults != null && iResults.size() > 0){
			result.setIndexResults(iResults);
			String status = result.getStatus();
			if(status == null){
				result.setStatus("INDEX");
			}else{
				result.setStatus(status + ",INDEX");
			}
			
		}
		if(conResults != null && conResults.size() > 0){
			result.setConstraintResults(conResults);
			String status = result.getStatus();
			if(status == null){
				result.setStatus("CONSTRAINT");
			}else{
				result.setStatus(status + ",CONSTRAINT");
			}
			
		}
		return result;
	}
}
