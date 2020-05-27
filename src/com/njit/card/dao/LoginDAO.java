package com.njit.card.dao;

import com.njit.card.entity.Login;

public interface LoginDAO {
	public   Login findById(long id);
	public void updataById(long id, String pasword) throws Exception; 
	public void addLogin(Login newLogin)throws Exception;
	public void delById(long loginId)throws Exception;
}
