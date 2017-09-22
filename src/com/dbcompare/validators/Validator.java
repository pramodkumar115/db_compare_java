package com.dbcompare.validators;

import java.sql.Connection;

public class Validator {

	protected Connection sourceCon;
	
	protected Connection destCon; 
	
	protected String sourceOwner; 
	
	protected String destOwner;
	
	public Validator(Connection sourceCon, Connection destCon, String sourceOwner, String destOwner){
		this.sourceCon = sourceCon;
		
		this.destCon = destCon;
		
		this.sourceOwner = sourceOwner;
		
		this.destOwner = destOwner;
	}
}
