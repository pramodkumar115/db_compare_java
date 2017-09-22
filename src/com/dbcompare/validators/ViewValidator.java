package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllViews;
import com.dbcompare.domain.ViewCompareResult;
import com.dbcompare.services.DDLHelper;

public class ViewValidator extends Validator {

	public ViewValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public List<ViewCompareResult> validate() {
		List<ViewCompareResult> resultList = new ArrayList<>();
		String query = "select owner, view_name, text from all_views where owner = ?";
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);

			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllViews> sourceViews = new ArrayList<>();
			List<AllViews> destViews = new ArrayList<>();

			while (sourceRes.next()) {
				AllViews sView = new AllViews();
				sView.setOwner(sourceRes.getString(1));
				sView.setViewName(sourceRes.getString(2));
				sView.setText(sourceRes.getString(3));
				sourceViews.add(sView);
			}

			while (destRes.next()) {
				AllViews dView = new AllViews();
				dView.setOwner(sourceRes.getString(1));
				dView.setViewName(sourceRes.getString(2));
				dView.setText(sourceRes.getString(3));
				destViews.add(dView);
			}
			for (AllViews sView : sourceViews) {
				boolean sourcePresent = false;
				for (AllViews dView : destViews) {
					if (sView.getViewName().equalsIgnoreCase(dView.getViewName())) {
						sourcePresent = true;
						break;
					}
				}
				if (!sourcePresent) {
					ViewCompareResult viewResult = new ViewCompareResult();
					viewResult.setViewName(sView.getViewName());
					StringBuilder message = new StringBuilder();
					viewResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					message.append(DDLHelper.getDDL("VIEW", sView.getViewName(), sourceCon));
					viewResult.setCompareMessage(message.toString());
					resultList.add(viewResult);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

}
