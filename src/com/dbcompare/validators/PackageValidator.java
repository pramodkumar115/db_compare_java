package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllPackages;
import com.dbcompare.domain.PackageCompareResult;
import com.dbcompare.domain.ProcedureCompareResult;
import com.dbcompare.services.DDLHelper;

public class PackageValidator extends Validator{

	public PackageValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public List<PackageCompareResult> validate() {
		List<PackageCompareResult> resultList = new ArrayList<>();
		String query = "select owner, OBJECT_NAME from ALL_OBJECTS where UPPER(OBJECT_TYPE) = 'PACKAGE' "
				+ " and owner = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllPackages> sourcePackages = new ArrayList<>();
			List<AllPackages> destPackages = new ArrayList<>();

			while (sourceRes.next()) {
				AllPackages sPackage = new AllPackages();
				sPackage.setOwner(sourceRes.getString(1));
				sPackage.setPackageName(sourceRes.getString(2));
				sourcePackages.add(sPackage);
			}

			while (destRes.next()) {
				AllPackages dPackage = new AllPackages();
				dPackage.setOwner(destRes.getString(1));
				dPackage.setPackageName(destRes.getString(2));
				destPackages.add(dPackage);
			}
			for (AllPackages sPackage : sourcePackages) {
				boolean sourcePresent = false;
				for (AllPackages dPackage : destPackages) {
					if (sPackage.getPackageName().equalsIgnoreCase(dPackage.getPackageName())) {
						sourcePresent = true;
						PackageCompareResult packageResult = validatePackage(sPackage, dPackage);
						if(packageResult.getStatus() != null && !"".equals(packageResult.getStatus())){
							resultList.add(packageResult);
						}
						break;
					}
				}
				if (!sourcePresent) {
					PackageCompareResult packageResult = new PackageCompareResult();
					packageResult.setPackageName(sPackage.getPackageName());
					StringBuilder message = new StringBuilder();
					packageResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					message.append(DDLHelper.getDDL("PACKAGE", sPackage.getPackageName(), sourceCon));
					packageResult.setCompareMessage(message.toString());
					resultList.add(packageResult);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	private PackageCompareResult validatePackage(AllPackages sPackage, AllPackages dPackage) {
		PackageCompareResult result = new PackageCompareResult();
		ProcedureValidator pValidator = new ProcedureValidator(sourceCon, destCon, sourceOwner, destOwner);
		List<ProcedureCompareResult> procedureResults = pValidator.validate(sPackage.getPackageName());
		if(procedureResults != null && procedureResults.size() > 0){
			result.setItemsResults(procedureResults);
		}
		return result;
	}

}
