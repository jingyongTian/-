package com.njit.card.test;

import com.njit.card.dao.LoginDAO;
import com.njit.card.dao.impl.LoginDAOImpl;
import com.njit.card.entity.Login;

public class TestLoginDAO { 
	public static void main(String[] args) {
		LoginDAO dao=new LoginDAOImpl();
		Login login=dao.findById(111);
		 System.out.println(login);
			}
		 
	}
 
