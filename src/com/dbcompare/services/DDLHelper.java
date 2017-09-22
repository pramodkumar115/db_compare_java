package com.dbcompare.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DDLHelper {

	public static String getDDL(String type, String name, Connection conn){
		PreparedStatement pstmt;
		String ddl = "";
		
		try {
			pstmt = conn.prepareStatement("select DBMS_METADATA.GET_DDL(?,?) from DUAL");
			pstmt.setString(1, type);
			pstmt.setString(2, name);
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				ddl = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddl;
	}
}
