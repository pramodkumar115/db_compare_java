package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllTabColumns;
import com.dbcompare.domain.ColumnCompareResult;

public class ColumnValidator extends Validator {

	public ColumnValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
		// TODO Auto-generated constructor stub
	}

	public List<ColumnCompareResult> validate(String tableName) {
		List<ColumnCompareResult> results = new ArrayList<>();
		String query = "select owner, table_name, column_name, data_type, data_length, nullable"
				+ " from all_tab_columns where owner = ? and table_name = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			sourceStmt.setString(2, tableName);
			destStmt.setString(2, tableName);

			List<AllTabColumns> sourceTableColumns = new ArrayList<>();
			List<AllTabColumns> destTableColumns = new ArrayList<>();

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			while (sourceRes.next()) {
				AllTabColumns sTableColumn = new AllTabColumns();
				sTableColumn.setOwner(sourceRes.getString(1));
				sTableColumn.setTableName(sourceRes.getString(2));
				sTableColumn.setColumnName(sourceRes.getString(3));
				sTableColumn.setDataType(sourceRes.getString(4));
				sTableColumn.setDataLength(sourceRes.getInt(5));
				sTableColumn.setNullable(sourceRes.getString(6));
				sourceTableColumns.add(sTableColumn);
			}

			while (destRes.next()) {
				AllTabColumns dTableColumn = new AllTabColumns();
				dTableColumn.setOwner(destRes.getString(1));
				dTableColumn.setTableName(destRes.getString(2));
				dTableColumn.setColumnName(destRes.getString(3));
				dTableColumn.setDataType(destRes.getString(4));
				dTableColumn.setDataLength(destRes.getInt(5));
				dTableColumn.setNullable(destRes.getString(6));
				destTableColumns.add(dTableColumn);
			}

			for (AllTabColumns sTableColumn : sourceTableColumns) {
				boolean sourcePresent = false;
				for (AllTabColumns dTableColumn : destTableColumns) {
					if (sTableColumn.getColumnName().equalsIgnoreCase(dTableColumn.getColumnName())) {
						sourcePresent = true;
						ColumnCompareResult cResult = validateColumn(sTableColumn, dTableColumn);
						if(cResult.getStatus() != null && !"".equals(cResult.getStatus())){
							results.add(cResult);
						}
						break;
					}
				}
				if (!sourcePresent) {
					ColumnCompareResult cResult = new ColumnCompareResult();
					cResult.setCompareMessage(sTableColumn.getColumnName());
					cResult.setColumnName(sTableColumn.getColumnName());
					cResult.setStatus("COLUMN_NOT_PRESENT");
					results.add(cResult);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	private ColumnCompareResult validateColumn(AllTabColumns sTableColumn, AllTabColumns dTableColumn) {
		ColumnCompareResult result = new ColumnCompareResult();
		StringBuilder compareMessage = new StringBuilder();
		StringBuilder status = new StringBuilder();
		if (!sTableColumn.getDataType().equalsIgnoreCase(dTableColumn.getDataType())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("DATATYPE_MISMATCH");
			compareMessage.append("DATATYPE: SOURCE - " + sTableColumn.getDataType() + ", DESTINATION - " + dTableColumn.getDataType());
		}
		if (sTableColumn.getDataLength() != dTableColumn.getDataLength()) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("DATALENGTH_MISMATCH");
			compareMessage.append("DATALENGTH: SOURCE - " + sTableColumn.getDataLength() + ", DESTINATION - " + dTableColumn.getDataLength());
		}
		if (!sTableColumn.getNullable().equalsIgnoreCase(dTableColumn.getNullable())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("NULLABLE_MISMATCH");
			compareMessage.append("NULLABLE: SOURCE - " + sTableColumn.getNullable() + ", DESTINATION - " + dTableColumn.getNullable());
		}
		result.setColumnName(sTableColumn.getColumnName());
		result.setStatus(status.toString());
		result.setCompareMessage(compareMessage.toString());
		return result;
	}
}
