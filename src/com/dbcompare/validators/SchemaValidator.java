package com.dbcompare.validators;

import java.sql.Connection;

import com.dbcompare.domain.SchemaCompareResult;

public class SchemaValidator extends Validator{

	public SchemaValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner) {
		super(sourceCon, destCon, sourceOwner, destOwner);
	}

	public SchemaCompareResult validate(){
		SchemaCompareResult result = new SchemaCompareResult();
		TableValidator tValidator = new TableValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setTableResultList(tValidator.validate());
		PackageValidator pValidator = new PackageValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setPackageResultList(pValidator.validate());
		ProcedureValidator procValidator = new ProcedureValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setProcedureResultList(procValidator.validate(null));
		FunctionValidator funValidator = new FunctionValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setFunctionResultList(funValidator.validate(null));
		ViewValidator vValidator = new ViewValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setViewResultList(vValidator.validate());
		SequenceValidator sValidator = new SequenceValidator(sourceCon, destCon, sourceOwner, destOwner);
		result.setSequenceResultList(sValidator.validate());
		return result;
	}
}
