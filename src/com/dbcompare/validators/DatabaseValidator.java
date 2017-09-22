package com.dbcompare.validators;

import java.sql.Connection;

import com.dbcompare.domain.DBCompareResult;
import com.dbcompare.domain.SchemaCompareResult;

public class DatabaseValidator extends Validator{
	public DatabaseValidator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner){
		super(sourceCon, destCon, sourceOwner, destOwner);
	}
	public DBCompareResult validate(){
		DBCompareResult result = new DBCompareResult();
		SchemaValidator sValidator = new SchemaValidator(sourceCon, destCon, sourceOwner, destOwner);
		SchemaCompareResult schemaResult = sValidator.validate();
		result.setSchemaResult(schemaResult);
		return result;
	}
}
