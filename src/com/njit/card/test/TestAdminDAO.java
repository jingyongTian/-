package com.njit.card.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.njit.card.dao.impl.AdminDAOImpl;
import com.njit.card.dao.impl.StudentDAOImpl;
import com.njit.card.entity.Card;
import com.njit.card.entity.Student;

public class TestAdminDAO {
	@Test
	public void testFindStudents() {
		AdminDAOImpl daoImpl = new AdminDAOImpl();
		List<Student> students = new ArrayList<Student>();
		try {
			students = daoImpl.findStudents();
			for (int i = 0; i < students.size(); i++) {
				Student s = students.get(i);
				System.out.println(s.getXingming());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public  void  testfindCardById(){
	 StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		System.out.println(daoImpl.findByCardId(1414010912).getBalance());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
	
	}
	@Test
	public  void  testFindCards(){
		AdminDAOImpl impl=new AdminDAOImpl();
	try {
		List<Card>cards=impl.findCrads();
		System.out.println(cards.size());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
	}
	@Test
	public    void testUpdateCard(){
		AdminDAOImpl impl=new AdminDAOImpl();
		Card card=new Card();
		card.setBalance(100.0);
		card.setMm("use");		 
		card.setCardstate(false);
		card.setCardid(1414010912L);
		try {
			impl.updateCard(card);
		} catch (Exception e) {			 
			e.printStackTrace();
		}		
	}
	@Test
	public   void  testAddCard(){
	AdminDAOImpl daoImpl=new AdminDAOImpl();
	Card c=new Card();
	c.setCardid(1414010914);
	try {
		daoImpl.addCard(c);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Test
	public   void testDelCard(){
		AdminDAOImpl daoImpl=new AdminDAOImpl();	 
		try {
			daoImpl.delCardById(1414010914);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
