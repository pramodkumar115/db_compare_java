package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllConstraints;
import com.dbcompare.domain.ConstraintCompareResult;

public class ConstraintValidator extends Validator {

	public ConstraintValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
		// TODO Auto-generated constructor stub
	}

	public List<ConstraintCompareResult> validate(String tableName) {
		List<ConstraintCompareResult> results = new ArrayList<>();
		String query = "SELECT ac.OWNER,ac.TABLE_NAME, ac.CONSTRAINT_NAME, ac.CONSTRAINT_TYPE,"
				+ "acc.COLUMN_NAME FROM all_constraints ac, all_cons_columns acc "
				+ "WHERE ac.CONSTRAINT_NAME = acc.CONSTRAINT_NAME AND ac.owner = ? AND ac.TABLE_NAME = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			sourceStmt.setString(2, tableName);
			destStmt.setString(2, tableName);

			List<AllConstraints> sourceConstraints = new ArrayList<>();
			List<AllConstraints> destConstraints = new ArrayList<>();

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			while (sourceRes.next()) {
				AllConstraints sConstraint = new AllConstraints();
				sConstraint.setOwner(sourceRes.getString(1));
				sConstraint.setTableName(sourceRes.getString(2));
				sConstraint.setConstraintName(sourceRes.getString(3));
				sConstraint.setConstraintType(sourceRes.getString(4));
				sConstraint.setColumnName(sourceRes.getString(5));
				sourceConstraints.add(sConstraint);
			}

			while (destRes.next()) {
				AllConstraints dConstraint = new AllConstraints();
				dConstraint.setOwner(destRes.getString(1));
				dConstraint.setTableName(destRes.getString(2));
				dConstraint.setConstraintName(destRes.getString(3));
				dConstraint.setConstraintType(destRes.getString(4));
				dConstraint.setColumnName(destRes.getString(5));
				destConstraints.add(dConstraint);
			}

			for (AllConstraints sConstraint : sourceConstraints) {
				boolean sourcePresent = false;
				for (AllConstraints dConstraint : destConstraints) {
					if (sConstraint.getConstraintName().equalsIgnoreCase(dConstraint.getConstraintName())) {
						sourcePresent = true;
						ConstraintCompareResult cResult = validateConstraint(sConstraint, dConstraint);
						if(cResult.getStatus() != null && !"".equals(cResult.getStatus())){
							results.add(cResult);
						}
						break;
					}
				}
				if (!sourcePresent) {
					ConstraintCompareResult cResult = new ConstraintCompareResult();
					cResult.setConstraintName(sConstraint.getConstraintName());
					cResult.setCompareMessage(sConstraint.getConstraintName());
					cResult.setStatus("CONSTRAINT_NOT_PRESENT");
					results.add(cResult);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	private ConstraintCompareResult validateConstraint(AllConstraints sConstraint, AllConstraints dConstraint) {
		ConstraintCompareResult result = new ConstraintCompareResult();
		StringBuilder compareMessage = new StringBuilder();
		StringBuilder status = new StringBuilder();
		if (!sConstraint.getColumnName().equalsIgnoreCase(dConstraint.getColumnName())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("COLUMN_NAME_MISMATCH");
			compareMessage.append("DATATYPE: SOURCE - " + sConstraint.getColumnName() + ", DESTINATION - " + dConstraint.getColumnName());
		}
		if (!sConstraint.getConstraintType().equalsIgnoreCase(dConstraint.getConstraintType())) {
			if(compareMessage.toString() != null && !"".equals(compareMessage.toString())){
				compareMessage.append("\n");
				status.append("\n");
			}
			status.append("CONSTRAINT_TYPE_MISMATCH");
			compareMessage.append("DATATYPE: SOURCE - " + sConstraint.getColumnName() + ", DESTINATION - " + dConstraint.getColumnName());
		}
		result.setConstraintName(sConstraint.getConstraintName());
		result.setStatus(status.toString());
		result.setCompareMessage(compareMessage.toString());
		return result;
	}
}
