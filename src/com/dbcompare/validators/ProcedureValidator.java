package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllProcedures;
import com.dbcompare.domain.ProcedureCompareResult;
import com.dbcompare.services.DDLHelper;

public class ProcedureValidator extends Validator{

	public ProcedureValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public List<ProcedureCompareResult> validate(String packageName) {
		List<ProcedureCompareResult> resultList = new ArrayList<>();
		String query = "select owner, object_name from all_procedures where "
				+ "object_type = 'PROCEDURE' and owner = ?";
		if(packageName != null && !"".equals(packageName)){
			query = "select * from all_procedures where object_type = 'PACKAGE' "
					+ "and owner = ? and object_name = ?";
		}
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);
			
			if(packageName != null && !"".equals(packageName)){
				sourceStmt.setString(2, packageName);
				destStmt.setString(2, packageName);
			}

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllProcedures> sourceProcedures = new ArrayList<>();
			List<AllProcedures> destProcedures = new ArrayList<>();

			while (sourceRes.next()) {
				AllProcedures sProcedure = new AllProcedures();
				sProcedure.setOwner(sourceRes.getString(1));
				sProcedure.setProcedureName(sourceRes.getString(2));
				sourceProcedures.add(sProcedure);
			}

			while (destRes.next()) {
				AllProcedures dProcedure = new AllProcedures();
				dProcedure.setOwner(destRes.getString(1));
				dProcedure.setProcedureName(destRes.getString(2));
				destProcedures.add(dProcedure);
			}
			for (AllProcedures sProcedure : sourceProcedures) {
				boolean sourcePresent = false;
				for (AllProcedures dProcedure : destProcedures) {
					if (sProcedure.getProcedureName().equalsIgnoreCase(dProcedure.getProcedureName())) {
						sourcePresent = true;
						break;
					}
				}
				if (!sourcePresent) {
					ProcedureCompareResult procedureResult = new ProcedureCompareResult();
					procedureResult.setProcedureName(sProcedure.getProcedureName());
					StringBuilder message = new StringBuilder();
					procedureResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					try{
					message.append(DDLHelper.getDDL("PROCEDURE", sProcedure.getProcedureName(), sourceCon));
					}catch(Exception e){
						// DO nothing
					}
					procedureResult.setCompareMessage(message.toString());
					resultList.add(procedureResult);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultList != null && resultList.size() > 0){
			return resultList;
		}else{
			return null;
		}
	}

	
}
