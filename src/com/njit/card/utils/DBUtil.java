 package com.njit.card.utils; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 

public class DBUtil {
	public static Connection getConnection() 
	throws Exception{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/card" +
						"?useUnicode=true&characterEncoding=utf8",
						"root","tjy123456");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return conn;
	}	
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	 
}
