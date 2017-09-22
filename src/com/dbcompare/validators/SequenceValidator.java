package com.dbcompare.validators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbcompare.domain.AllSequences;
import com.dbcompare.domain.SequenceCompareResult;
import com.dbcompare.services.DDLHelper;

public class SequenceValidator extends Validator{

	public SequenceValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public List<SequenceCompareResult> validate() {
		List<SequenceCompareResult> resultList = new ArrayList<>();
		String query = "select sequence_owner, sequence_name, "
				+ "increment_by, cache_size from all_sequences where "
				+ "sequence_owner = ? ";
		
		try {
			PreparedStatement sourceStmt = sourceCon.prepareStatement(query);
			PreparedStatement destStmt = destCon.prepareStatement(query);

			sourceStmt.setString(1, sourceOwner);
			destStmt.setString(1, destOwner);
			
			ResultSet sourceRes = sourceStmt.executeQuery();
			ResultSet destRes = destStmt.executeQuery();

			List<AllSequences> sourceSequences = new ArrayList<>();
			List<AllSequences> destSequences = new ArrayList<>();

			while (sourceRes.next()) {
				AllSequences sSequence = new AllSequences();
				sSequence.setOwner(sourceRes.getString(1));
				sSequence.setSequenceName(sourceRes.getString(2));
				sSequence.setIncrementBy(sourceRes.getInt(3));
				sSequence.setCacheSize(sourceRes.getInt(4));
				sourceSequences.add(sSequence);
			}

			while (destRes.next()) {
				AllSequences dSequence = new AllSequences();
				dSequence.setOwner(destRes.getString(1));
				dSequence.setSequenceName(destRes.getString(2));
				dSequence.setIncrementBy(destRes.getInt(3));
				dSequence.setCacheSize(destRes.getInt(4));
				destSequences.add(dSequence);
			}
			for (AllSequences sSequence : sourceSequences) {
				boolean sourcePresent = false;
				for (AllSequences dSequence : destSequences) {
					if (sSequence.getSequenceName().equalsIgnoreCase(dSequence.getSequenceName())) {
						sourcePresent = true;
						break;
					}
				}
				if (!sourcePresent) {
					SequenceCompareResult sequenceResult = new SequenceCompareResult();
					sequenceResult.setSequenceName(sSequence.getSequenceName());
					StringBuilder message = new StringBuilder();
					sequenceResult.setStatus("NOT_PRESENT_IN_DESTINATION");
					message.append(DDLHelper.getDDL("SEQUENCE", sSequence.getSequenceName(), sourceCon));
					sequenceResult.setCompareMessage(message.toString());
					resultList.add(sequenceResult);
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
