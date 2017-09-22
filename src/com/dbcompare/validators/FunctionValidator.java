package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllFunctions;
import com.dbcompare.domain.FunctionCompareResult;
import com.dbcompare.services.DDLHelper;

public class FunctionValidator extends Validator{

	public FunctionValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public List<FunctionCompareResult> validate(String packageName) {
		List<FunctionCompareResult> resultList = new ArrayList<>();
		String query = "select owner, object_name from all_procedures where "
				+ "object_type = 'FUNCTION' and owner = ?";
		if(packageName != null && !"".equals(packageName)){
			query = "select * from all_procedures where object_type = 'PACKAGE' "
					+ "and owner = ? and object_name = ?";
		}
		
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);
			
			if(packageName != null && !("".equals(packageName))){
				sourceStmt.setString(2, packageName);
				destStmt.setString(2, packageName);
			}

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllFunctions> sourceFunctions = new ArrayList<>();
			List<AllFunctions> destFunctions = new ArrayList<>();

			while (sourceRes.next()) {
				AllFunctions sFunction = new AllFunctions();
				sFunction.setOwner(sourceRes.getString(1));
				sFunction.setFunctionName(sourceRes.getString(2));
				sourceFunctions.add(sFunction);
			}

			while (destRes.next()) {
				AllFunctions dFunction = new AllFunctions();
				dFunction.setOwner(destRes.getString(1));
				dFunction.setFunctionName(destRes.getString(2));
				destFunctions.add(dFunction);
			}
			for (AllFunctions sFunction : sourceFunctions) {
				boolean sourcePresent = false;
				for (AllFunctions dFunction : destFunctions) {
					if (sFunction.getFunctionName().equalsIgnoreCase(dFunction.getFunctionName())) {
						sourcePresent = true;
						break;
					}
				}
				if (!sourcePresent) {
					FunctionCompareResult functionResult = new FunctionCompareResult();
					functionResult.setFunctionName(sFunction.getFunctionName());
					StringBuilder message = new StringBuilder();
					functionResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					message.append(DDLHelper.getDDL("FUNCTION", sFunction.getFunctionName(), sourceCon));
					functionResult.setCompareMessage(message.toString());
					resultList.add(functionResult);
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
