package com.njit.card.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.njit.card.dao.EducationDAO;
import com.njit.card.utils.DBUtil;

public class EducationDAOImpl implements EducationDAO{
	public void delFoodRecordsById(long cardid,long foodid) throws Exception {
	Connection conn=null;
	conn=DBUtil.getConnection();
	String sql="delete  from costrecord where cardid=? and foodid=?";
	PreparedStatement prep=conn.prepareStatement(sql);
	prep.setLong(1,cardid);
	prep.setLong(2,foodid);
	prep.execute();
		
	}

}
