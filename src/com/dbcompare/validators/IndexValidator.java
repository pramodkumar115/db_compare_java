package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllIndexes;
import com.dbcompare.domain.IndexCompareResult;

public class IndexValidator extends Validator{


	public IndexValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
		// TODO Auto-generated constructor stub
	}

	public List<IndexCompareResult> validate(String tableName) {
		List<IndexCompareResult> results = new ArrayList<>();
		String query = "SELECT ai.OWNER, ai.TABLE_NAME, ai.INDEX_NAME, ai.INDEX_TYPE, ai.UNIQUENESS, ai.COMPRESSION,"+
				"aic.COLUMN_NAME FROM all_indexes ai, all_ind_columns aic WHERE ai.INDEX_NAME = aic.INDEX_NAME AND "
				+ "ai.owner = ? and ai.table_name = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			sourceStmt.setString(2, tableName);
			destStmt.setString(2, tableName);

			List<AllIndexes> sourceTableIndexes = new ArrayList<>();
			List<AllIndexes> destTableIndexes = new ArrayList<>();

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			while (sourceRes.next()) {
				AllIndexes sTableIndex = new AllIndexes();
				sTableIndex.setOwner(sourceRes.getString(1));
				sTableIndex.setTableName(sourceRes.getString(2));
				sTableIndex.setIndexName(sourceRes.getString(3));
				sTableIndex.setIndexType(sourceRes.getString(4));
				sTableIndex.setUniqueness(sourceRes.getString(5));
				sTableIndex.setCompression(sourceRes.getString(6));
				sTableIndex.setColumnName(sourceRes.getString(7));
				sourceTableIndexes.add(sTableIndex);
			}

			while (destRes.next()) {
				AllIndexes dTableIndex = new AllIndexes();
				dTableIndex.setOwner(destRes.getString(1));
				dTableIndex.setTableName(destRes.getString(2));
				dTableIndex.setIndexName(destRes.getString(3));
				dTableIndex.setIndexType(destRes.getString(4));
				dTableIndex.setUniqueness(destRes.getString(5));
				dTableIndex.setCompression(destRes.getString(6));
				dTableIndex.setColumnName(destRes.getString(7));
				destTableIndexes.add(dTableIndex);
			}

			for (AllIndexes sTableIndex : sourceTableIndexes) {
				boolean sourcePresent = false;
				for (AllIndexes dTableIndex : destTableIndexes) {
					if (sTableIndex.getIndexName().equalsIgnoreCase(dTableIndex.getIndexName())) {
						sourcePresent = true;
						IndexCompareResult cResult = validateIndex(sTableIndex, dTableIndex);
						if(cResult.getStatus() != null && !"".equals(cResult.getStatus())){
							results.add(cResult);
						}
						break;
					}
				}
				if (!sourcePresent) {
					IndexCompareResult iResult = new IndexCompareResult();
					iResult.setIndexName(sTableIndex.getIndexName());
					iResult.setCompareMessage(sTableIndex.getIndexName());
					iResult.setStatus("INDEX_NOT_PRESENT");
					results.add(iResult);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	private IndexCompareResult validateIndex(AllIndexes sTableIndex, AllIndexes dTableIndex) {
		IndexCompareResult result = new IndexCompareResult();
		StringBuilder compareMessage = new StringBuilder();
		StringBuilder status = new StringBuilder();
		if (!sTableIndex.getIndexType().equalsIgnoreCase(dTableIndex.getIndexType())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("INDEXTYPE_MISMATCH");
			compareMessage.append("INDEXTYPE: SOURCE - " + sTableIndex.getIndexType() + ", DESTINATION - " + dTableIndex.getIndexType());
		}
		if (!sTableIndex.getUniqueness().equalsIgnoreCase(dTableIndex.getUniqueness())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("UNIQUENESS_MISMATCH");
			compareMessage.append("UNIQUENESS: SOURCE - " + sTableIndex.getUniqueness() + ", DESTINATION - " + dTableIndex.getUniqueness());
		}
		if (!sTableIndex.getCompression().equalsIgnoreCase(dTableIndex.getCompression())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("COMPRESSION_MISMATCH");
			compareMessage.append("COMPRESSION: SOURCE - " + sTableIndex.getCompression() + ", DESTINATION - " + dTableIndex.getCompression());
		}
		if (!sTableIndex.getColumnName().equalsIgnoreCase(dTableIndex.getColumnName())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("COLUMN_MISMATCH");
			compareMessage.append("COLUMN: SOURCE - " + sTableIndex.getColumnName() + ", DESTINATION - " + dTableIndex.getColumnName());
		}
		
		result.setIndexName(sTableIndex.getIndexName());
		result.setStatus(status.toString());
		result.setCompareMessage(compareMessage.toString());
		return result;
	}
}
