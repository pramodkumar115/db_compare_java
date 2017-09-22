package com.dbcompare.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dbcompare.domain.Configuration;
import com.dbcompare.domain.DBCompareResult;
import com.dbcompare.domain.DBConfig;
import com.dbcompare.validators.DatabaseValidator;

public class DBCompareHelper {
	//public static void main(String[] args) {
	public DBCompareResult compare(Configuration config){
		try {
			DBConfig sourceConfig = config.getSourceConfig();
			DBConfig destConfig = config.getDestConfig();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection sourceCon = DriverManager.getConnection("jdbc:oracle:thin:@"+sourceConfig.getHost()+":"+sourceConfig.getPort()+
					":"+sourceConfig.getServiceName(),sourceConfig.getUserName(),sourceConfig.getPassword());
			String sourceOwner = sourceConfig.getDbOwner();
			Connection destCon = DriverManager.getConnection("jdbc:oracle:thin:@"+destConfig.getHost()+":"+destConfig.getPort()+
					":"+destConfig.getServiceName(),destConfig.getUserName(),destConfig.getPassword());
			//Connection destCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DB1", "DB1");
			String destOwner = destConfig.getDbOwner();
			
			DatabaseValidator validator = new DatabaseValidator(sourceCon, destCon, sourceOwner, destOwner);
			DBCompareResult result = validator.validate();
			/*ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(result);
			System.out.println(json);*/
			sourceCon.close();
			destCon.close();
			return result;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
