package com.njit.card.test;
import com.mysql.jdbc.Connection;
import com.njit.card.utils.DBUtil;

public class TestDBUtil {
	public static void main(String[] args)   {
		DBUtil dbUtil=new DBUtil();
		Connection connection=null;
		try {
		 connection=(Connection)dbUtil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( connection);
	}

}
