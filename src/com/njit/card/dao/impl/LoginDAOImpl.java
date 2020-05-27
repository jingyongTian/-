package com.njit.card.dao.impl;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.njit.card.dao.LoginDAO;
import com.njit.card.entity.Login;
import com.njit.card.utils.DBUtil;

public class LoginDAOImpl implements LoginDAO {

	
	public  Login findById(long id) {
		 Connection conn=null;
		 Login  login=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from  login where id=?";
			PreparedStatement prep=conn.prepareStatement(sql);
			prep.setLong(1, id);
			ResultSet rst=prep.executeQuery();			 
			while(rst.next()){
				login=new Login();
				login.setId(id);
				login.setMm(rst.getString("mm"));
				login.setType(rst.getInt("type"));			 
				
			}
			
		} catch (Exception e) {			 
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		return login;
	}

	@Override
	public void updataById(long id, String pasword) throws Exception{
		// TODO 自动生成的方法存根
		Connection conn=null;
		 conn=DBUtil.getConnection();
		 System.out.println(id+" "+ pasword);
		 String sql="update login set mm=? where id=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setString(1,pasword);
		 prep.setDouble(2,id);
		 prep.execute();	
		 DBUtil.close(conn);
	}

	@Override
	public void addLogin(Login newLogin) throws Exception {
		// TODO 自动生成的方法存根
		Connection conn=null;
		 conn=DBUtil.getConnection();
		 String sql="insert into login(id,mm,type) values(?,?,?)";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,newLogin.getId());
		 prep.setString(2,newLogin.getMm());
		 prep.setInt(3, newLogin.getType());
		 prep.execute();	
		 DBUtil.close(conn);
	}

	@Override
	public void delById(long loginId) throws Exception {
		// TODO 自动生成的方法存根
		Connection conn=null;
		conn=DBUtil.getConnection();
		String  sql="delete from login where id=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setLong(1,loginId);
		prep.execute();
		DBUtil.close(conn);
	}

}
